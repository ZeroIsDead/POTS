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
public class Payment extends Item {

    public Payment(List<String> Details, String Type, DataContainer reader, DataWriter writer) {
        super(Details, Type, reader, writer);
    }

    @Override
    public List<Item> getUpwardsRelatedItems(String Type) {
        return null;
    }

    @Override
    public List<Item> getDownwardsRelatedItems(String Type) {
        switch (Type) {
            case "PO" -> {
                return this.getPORelatedToPayment();
            }
            case "PR" -> {
                List<Item> POList = this.getDownwardsRelatedItems("PO");
                
                List<Item> PRList = new ArrayList<>();
                
                for (Item currentPO : POList) {
                    List<Item> PRRelatedToPO = currentPO.getDownwardsRelatedItems("PR");
                    
                    PRList.addAll(PRRelatedToPO);
                }
                
                return PRList;
            }
            case "Product" -> {
                List<Item> PRList = this.getDownwardsRelatedItems("PR");
                
                List<Item> ProductList = new ArrayList<>();
                
                for (Item currentPR : PRList) {
                    List<Item> ProductRelatedToPR = currentPR.getDownwardsRelatedItems("Product");
                    
                    ProductList.addAll(ProductRelatedToPR);
                }
                
                return ProductList;
            }
            
            default -> {
                return null;
            }
        }
    }
    
    @Override
    public void addRelatedItem(Item newItem) {
        if (!newItem.getType().equals("PO")) {
            return;
        }
        
        this.writer.setFilePath("POToPayment");
        
        List<String> FileDataFormat = new ArrayList<>();
        
        FileDataFormat.add(this.getID());
        FileDataFormat.add(newItem.getID());
        
        this.writer.appendData(FileDataFormat);
    }

    @Override
    public void deleteRelatedItem(Item newItem) {
        if (!newItem.getType().equals("PO")) {
            return;
        }
        
        this.writer.setFilePath("POToPayment");
        
        List<String> FileDataFormat = new ArrayList<>();
        
        FileDataFormat.add(this.getID());
        FileDataFormat.add(newItem.getID());
        
        this.writer.deleteCompositeData(FileDataFormat);
    }
    
    private List<Item> getPORelatedToPayment() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        this.reader.setFilePath("POToPayment");
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> Relationship = this.reader.FitlerData("PaymentID", ID);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(1)); // get ID of All Related Items 
        }

//        Read Product File And Get All Related Products To PR/Sales
        this.reader.setFilePath("PO");

        List<List<String>> ItemDetailList = reader.FitlerData("POID", RelatedItemIDList);

        List<Item> ItemList = new ArrayList<>();

        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(RowData, "PO", this.reader, this.writer);
            ItemList.add(newItem);
        }

        return ItemList;
    }

}
