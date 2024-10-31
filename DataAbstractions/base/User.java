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
public class User extends Item {

    public User(List<String> Details, String Type, DataContainer reader, DataWriter writer) {
        super(Details, Type, reader, writer);
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
    public void addRelatedItem(Item newItem) {}

    @Override
    public void deleteRelatedItem(Item newItem) {}
    
    private List<Item> getItemRelatedToUser(String Type) {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        this.reader.setFileName(Type);
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> ItemDetailList = this.reader.FitlerData("UserID", ID);
        
        if (ItemDetailList.isEmpty()) {
            return null;
        }
        
        List<Item> ItemList = new ArrayList<>();
        
        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(RowData, Type, this.reader, this.writer);
            ItemList.add(newItem);
        }

        return ItemList;
    }

    @Override
    public Boolean CanBeDeleted() {
        
        List<String> DownwardsRelations = Arrays.asList("PR", "PO");
        
        for (String Relations : DownwardsRelations) {
            List<Item> RelatedItems = this.getDownwardsRelatedItems(Relations);
            
            if (RelatedItems.isEmpty()) {
                continue; 
            }
            
            for (Item currentItem : RelatedItems) {
                if (!currentItem.CanBeDeleted()) {
                    return false;
                }
            }
        }
        
        return true;
    }

}
