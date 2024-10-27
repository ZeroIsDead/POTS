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
public class PO extends Item {
    public PO(List<String> Details, String Type, DataContainer reader, DataWriter writer) {
        super(Details, Type, reader, writer);
    }
    
    public void fuck() {
        
    }

    @Override
    public List<Item> getUpwardsRelatedItems(String Type) {
        switch (Type) {
            case "Payment" -> {
                return this.getPaymentRelatedToPO();
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public List<Item> getDownwardsRelatedItems(String Type) {
        switch (Type) {
            case "PR" -> {
                return this.getPRRelatedToPO();
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
        if (!newItem.getType().equals("PR")) {
            return;
        }
        
        this.writer.setFilePath("PRToPO");
        
        List<String> FileDataFormat = new ArrayList<>();
        
        FileDataFormat.add(this.getID());
        FileDataFormat.add(newItem.getID());
        
        this.writer.appendData(FileDataFormat);
    }

    @Override
    public void deleteRelatedItem(Item newItem) {
        if (!newItem.getType().equals("PR")) {
            return;
        }
        
        this.writer.setFilePath("PRToPO");
        
        List<String> FileDataFormat = new ArrayList<>();
        
        FileDataFormat.add(this.getID());
        FileDataFormat.add(newItem.getID());
        
        this.writer.deleteCompositeData(FileDataFormat);
    }
    
    private List<Item> getPaymentRelatedToPO() {
        String ID = this.getID();
        
//        Reading The Relationship File
        this.reader.setFilePath("POToPayment");
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> Relationship = this.reader.FitlerData("POID", ID);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(0)); // get ID of All Related Items 
        }

//        Read Product File And Get All Related Products To PR/Sales
        this.reader.setFilePath("Payment");

        List<List<String>> ItemDetailList = this.reader.FitlerData("PaymentID", RelatedItemIDList);

        List<Item> ItemList = new ArrayList<>();

        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(RowData, "Payment", this.reader, this.writer);
            ItemList.add(newItem);
        }

        return ItemList;
    }
    
    private List<Item> getPRRelatedToPO() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        this.reader.setFilePath("PRToPO");
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> Relationship = this.reader.FitlerData("POID", ID);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(1)); // get ID of All Related Items 
        }

//        Read Product File And Get All Related Products To PR/Sales
        this.reader.setFilePath("PR");

        List<List<String>> ItemDetailList = this.reader.FitlerData("PRID", RelatedItemIDList);

        List<Item> ItemList = new ArrayList<>();

        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(RowData, "PR", this.reader, this.writer);
            ItemList.add(newItem);
        }

        return ItemList;
    }

}
