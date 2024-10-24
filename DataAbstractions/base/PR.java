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
public class PR extends Item {

    public PR(List<String> FieldNames, List<String> Details, String Type) {
        super(FieldNames, Details, Type);
    }

    @Override
    public List<Item> getUpwardsRelatedItems(String Type) {
        switch (Type) {
            case "PO" -> {
                return this.getPORelatedToPR();
            }
            case "Payment" -> {
                List<Item> POList = this.getUpwardsRelatedItems("PO");
                
                List<Item> PaymentList = new ArrayList<>();
                
                for (Item currentPO : POList) {
                    List<Item> PaymentRelatedToPO = currentPO.getUpwardsRelatedItems("Payment");
                    
                    PaymentList.addAll(PaymentRelatedToPO);
                }
                
                return PaymentList;
            }

            default -> {
                return null;
            }
        }
    }

    @Override
    public List<Item> getDownwardsRelatedItems(String Type) {
        switch (Type) {
            case "Product" -> {
                return this.getProductsRelatedToPR();
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
    
    private List<Item> getPORelatedToPR() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        DataContainer CollectionReader = new FileHandler("PRToPO");
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> Relationship = CollectionReader.FitlerData("PRID", ID);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(0)); // get ID of All Related Items 
        }

//        Read Product File And Get All Related Products To PR/Sales
        DataContainer reader = new FileHandler("PO");

        List<List<String>> ItemDetailList = reader.FitlerData("POID", RelatedItemIDList);

        List<Item> ItemList = new ArrayList<>();

        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(reader.getFieldName(), RowData, "PO");
            ItemList.add(newItem);
        }

        return ItemList;
    }
    
    private List<Item> getProductsRelatedToPR() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        DataContainer CollectionReader = new FileHandler("ProductToPR");
        
//        Get Wanted Field to Help Filter Out All Relationships For - Product To PR / Product List
        String RelationshipWantedFields = CollectionReader.getFieldName().get(0);

//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> Relationship = CollectionReader.FitlerData(RelationshipWantedFields, ID);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        String ItemWantedField = CollectionReader.getFieldName().get(1); // POID / PRID / ProductID
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(1)); // get ID of All Related Items 
        }
//        Get The Quantity Of Product Sold/Bought With Its Index
        List<String> ItemQuantities = CollectionReader.getColumn("Quantity");
        int WantedFieldIndex = CollectionReader.getFieldName().indexOf("Quantity");

//        Read Product File And Get All Related Products To PR/Sales
        DataContainer reader = new FileHandler("Product");

        List<List<String>> ItemDetailList = reader.FitlerData(ItemWantedField, RelatedItemIDList);

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
