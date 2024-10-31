/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions.base;

import DataAbstractions.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class Sales extends Item {

    public Sales(List<String> Details, String Type, DataContainer reader, DataWriter writer) {
        super(Details, Type, reader, writer);
    }

    @Override
    public List<Item> getUpwardsRelatedItems(String Type) {
        return null;
    }

    @Override
    public List<Item> getDownwardsRelatedItems(String Type) {
        switch (Type) {
            case "Product" -> {
                return this.getProductsRelatedToSale();
            }
            default -> {
                return null;
            }
        }
    }
    
    @Override
    public void addRelatedItem(Item newItem) {
        if (!newItem.getType().equals("Product")) {
            return;
        }

//        A Sale can only contain Products of the same Supplier
        if (this.getFieldValue("SupplierID").equals(newItem.getFieldValue("SupplierID"))) {
            return;
        }
        
        this.writer.setFileName("ProductToSales");
        
        List<String> FileDataFormat = new ArrayList<>();
        
        FileDataFormat.add(this.getID());
        FileDataFormat.add(newItem.getID());
        FileDataFormat.add(newItem.getDetails().get(0)); // Quantity of Item
        
        this.writer.appendData(FileDataFormat);
    }

    @Override
    public void deleteRelatedItem(Item newItem) {
        if (!newItem.getType().equals("Product")) {
            return;
        }
        
        this.writer.setFileName("ProductToSales");
        
        List<String> FileDataFormat = new ArrayList<>();
        
        FileDataFormat.add(this.getID());
        FileDataFormat.add(newItem.getID());
        FileDataFormat.add(newItem.getDetails().get(0)); // Quantity of Item
        
        this.writer.deleteCompositeData(FileDataFormat);
    }
    
    private List<Item> getProductsRelatedToSale() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        this.reader.setFileName("ProductToSales");
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> Relationship = this.reader.FitlerData("SalesID", ID);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(1)); // get ID of All Related Items 
        }
//        Get The Quantity Of Product Sold/Bought
        List<String> ItemQuantities = this.reader.getColumn("Quantity");

//        Read Product File And Get All Related Products To PR/Sales
        this.reader.setFileName("Product");
        
        int WantedFieldIndex = this.reader.getFieldName().indexOf("Quantity");

        List<List<String>> ItemDetailList = this.reader.FitlerData("ProductID", RelatedItemIDList);

        List<Item> ItemList = new ArrayList<>();

        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

//            Replace The Quantity Of The Product In Inventory To The Quantity Sold/Bought
            RowData.remove(WantedFieldIndex);
            RowData.add(WantedFieldIndex, ItemQuantities.get(i));
            
            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(RowData, "Product", this.reader, this.writer);
            ItemList.add(newItem);
        }

        return ItemList;
    }

    @Override
    public Boolean CanBeDeleted() {
        return true;
    }

}
