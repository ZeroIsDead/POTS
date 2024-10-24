/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions.base;

import DataAbstractions.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class Sales extends Item {

    public Sales(List<String> FieldNames, List<String> Details, String Type) {
        super(FieldNames, Details, Type);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateRelatedItem(Item newItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteRelatedItem(Item newItem) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private List<Item> getProductsRelatedToSale() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        DataContainer CollectionReader = new FileHandler("ProductToSales");
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> Relationship = CollectionReader.FitlerData("SalesID", ID);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(1)); // get ID of All Related Items 
        }
//        Get The Quantity Of Product Sold/Bought With Its Index
        List<String> ItemQuantities = CollectionReader.getColumn("Quantity");
        int WantedFieldIndex = CollectionReader.getFieldName().indexOf("Quantity");

//        Read Product File And Get All Related Products To PR/Sales
        DataContainer reader = new FileHandler("Product");

        List<List<String>> ItemDetailList = reader.FitlerData("ProductID", RelatedItemIDList);

        List<Item> ItemList = new ArrayList<>();

        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

//            Replace The Quantity Of The Product In Inventory To The Quantity Sold/Bought
            RowData.remove(WantedFieldIndex);
            RowData.add(WantedFieldIndex, ItemQuantities.get(i));
            
            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(reader.getFieldName(), RowData, "Product");
            ItemList.add(newItem);
        }

        return ItemList;
    }

}
