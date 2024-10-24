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

    public Payment(List<String> FieldNames, List<String> Details, String Type) {
        super(FieldNames, Details, Type);
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
    
    private List<Item> getPORelatedToPayment() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        DataContainer CollectionReader = new FileHandler("POToPayment");
        
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

//        Read Product File And Get All Related Products To PR/Sales
        DataContainer reader = new FileHandler("PO");

        List<List<String>> ItemDetailList = reader.FitlerData(ItemWantedField, RelatedItemIDList);

        List<Item> ItemList = new ArrayList<>();

        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(reader.getFieldName(), RowData, "PO");
            ItemList.add(newItem);
        }

        return ItemList;
    }

}
