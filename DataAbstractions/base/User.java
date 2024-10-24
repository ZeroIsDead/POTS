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
public class User extends Item {

    public User(List<String> FieldNames, List<String> Details, String Type) {
        super(FieldNames, Details, Type);
    }

    @Override
    public List<Item> getUpwardsRelatedItems(String Type) {
        return null;
    }

    @Override
    public List<Item> getDownwardsRelatedItems(String Type) {
        switch (Type) {
            case "PO", "PR", "Sales" -> {
                return this.getItemRelatedToUser(Type);
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
    
    private List<Item> getItemRelatedToUser(String Type) {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        DataContainer CollectionReader = new FileHandler(Type);
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> ItemDetailList = CollectionReader.FitlerData("UserID", ID);
        
        if (ItemDetailList.isEmpty()) {
            return null;
        }
        
        List<Item> ItemList = new ArrayList<>();
        
        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(CollectionReader.getFieldName(), RowData, Type);
            ItemList.add(newItem);
        }

        return ItemList;
    }

}
