/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions;

import DataAbstractions.base.DataContainer;
import DataAbstractions.base.DataWriter;
import DataAbstractions.base.ItemFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class ItemCollection {
    private final List<String> FieldNames;
    private final List<Item> ItemList;
    private final DataContainer reader;
    private final DataWriter writer;
    private final ItemFactory Factory;
    private final String Type;

    public ItemCollection(List<Item> newItemList, DataContainer newReader, DataWriter newWriter, ItemFactory newFactory, String Type) {
        this.ItemList = newItemList;
        this.reader = newReader;
        this.writer = newWriter;
        this.FieldNames = this.reader.getFieldName();
        this.Factory = newFactory;
        this.Type = Type;
        
    }
    
    @Override
    public String toString() {
        String Output = "";
        
        int count = 1;
        for (Item CurrentItem : this.ItemList) {
            Output += String.format("%d\n%s\n", count++, CurrentItem);
        } 
        return Output;
    }
    
    private void UpdateFile() {
        List<List<String>> Data = new ArrayList<>(); // Turn Item Into Writable Format
        
        this.ItemList.forEach((Item currentItem) -> {
            Data.add(currentItem.getDetails());
        });
        
        this.writer.setFileName(this.Type);
        
        this.writer.writeData(Data);
    }
    
    public int getCollectionSize() {
        return this.ItemList.size();
    }
    
    public Boolean CheckItemInCollection(Item ItemInstance) {
        for (Item CurrentItem : this.ItemList) {
            if (CurrentItem.getDetails().equals(ItemInstance.getDetails())) {
                return true;
            }
        }
        
        return false;
    }
    
    public Boolean CheckItemInCollection(List<String> Details) {
        for (Item CurrentItem : this.ItemList) {
            if (CurrentItem.getDetails().equals(Details)) {
                return true;
            }
        }
        
        return false;
    }
    
    private int getItemIndex(Item ItemInstance) {
        String ItemInstanceID = ItemInstance.getDetails().get(0); // Get Item ID
        
        for (int i = 0; i < this.ItemList.size(); i++) {
            String CurrentItemID = this.ItemList.get(i).getDetails().get(0); // get Item ID
            
            if (CurrentItemID.equals(ItemInstanceID)) { // Matches IDs
                return i;
            }
        }
        
        return -1;
    }
    
    private int getItemIndex(String ID) {
        for (int i = 0; i < this.ItemList.size(); i++) {
            String CurrentItemID = this.ItemList.get(i).getDetails().get(0);
            
            if (CurrentItemID.equals(ID)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public Item createItem(List<String> Details) {
        //Check if item exist in list and If Data Size Matches
        if (Details.size() != this.FieldNames.size() || this.CheckItemInCollection(Details)) {
            return null;
        }
        
        Item newItem = this.Factory.createItem(Details, this.Type, this.reader, this.writer);
        
        this.ItemList.add(newItem);
        this.UpdateFile();
        
        return newItem;
    }
    
    public Item createItem(String[] DetailsArray) {
        List<String> Details = Arrays.asList(DetailsArray);
        
        return this.createItem(Details);
    }
    
    public void updateItem(Item ItemInstance) {
        
        int itemIndex = this.getItemIndex(ItemInstance);
        
//        Check if ID is Same But The Details Are Different
        if (itemIndex != -1 && !this.CheckItemInCollection(ItemInstance)) {
            return;
        }
        
        this.ItemList.remove(itemIndex);
        this.ItemList.add(itemIndex, ItemInstance);
        this.UpdateFile();
    }
    
    public Boolean removeItem(Item ItemInstance) {
        int count = 0;
        for (Item currentItem : this.ItemList) {
            if (currentItem.equals(ItemInstance)) {
                
//                If Item Has Any Relationship or Item has Certain Status Then No Delete
                if (!currentItem.CanBeDeleted()) { 
                    return false;
                }
                
                this.ItemList.remove(count);
                this.UpdateFile();

                return true;
            }
            count++;
        }
        
        return false;
    }
    
    public Boolean removeItem(String ID) {
        
        int count = 0;
        for (Item currentItem : this.ItemList) {
            List<String> currentItemDetails = currentItem.getDetails();
            
            if (currentItemDetails.get(0).equals(ID)) {
                
//                If Item Has Any Relationship or Item has Certain Status Then No Delete
                if (!currentItem.CanBeDeleted()) { 
                    return false;
                }
                
                this.ItemList.remove(count);
                this.UpdateFile();

                return true;
            }
            count++;
        }
        
        return false;
    }
    
    public List<String> getColumn(String Field) {
        int WantedFieldIndex = this.FieldNames.indexOf(Field);
        
        List<String> ColumnData = new ArrayList<>();
        
        for (Item CurrentItem : this.ItemList) {
            String CurrentItemFieldValue = CurrentItem.getDetails().get(WantedFieldIndex);
            ColumnData.add(CurrentItemFieldValue);
        }
        
        return ColumnData;
    }
    
    public List<String> getFieldNames() {
        return this.FieldNames;
    }
    
    public List<Item> getAll() {
        return this.ItemList;
    }

    public Item getItem(String ID) {
        int index = this.getItemIndex(ID);
        
        if (index == -1) {
            return null;
        }
        
        return this.ItemList.get(index);
    }
    
    public List<Item> filter(List<String> Fields, List<String> Values) { // Field and Values Match 1 to 1 -> index 0 with index 0, etc
        List<Integer> indexes = new ArrayList<>();
        
//        Creates a List of n 1s;
        indexes.addAll(Collections.nCopies(this.getCollectionSize(), 1));
        
        
//        If Value in the Corresponding Field and Row is Different, Then Remove
        for (int i = 0; i < Fields.size() && i < Values.size(); i++) {
            List<String> ColumnData = this.getColumn(Fields.get(i));
            
            String FieldValue = Values.get(i);
            
            for (int j = 0; j < ColumnData.size(); j++) {
                if (indexes.get(j) != 0 && !ColumnData.get(j).equals(FieldValue)) {
                    indexes.remove(j);
                    indexes.add(j, 0);
                }
            } 
        }
        
        List<Item> FilteredItemList = new ArrayList<>();
        
        for (int i = 0; i < indexes.size(); i++) {
            if (indexes.get(i) == 1) {
                FilteredItemList.add(this.ItemList.get(i));
            }
        }
        
        return FilteredItemList;
    }   
    
    public List<Item> filter(String Field, List<String> Values) {
        List<Item> FilteredItemList = new ArrayList<>();
        
        List<String> ColumnData = this.getColumn(Field);
        
        
        for (int i = 0; i < ColumnData.size(); i++) {
            if (Values.contains(ColumnData.get(i))) {
                FilteredItemList.add(this.ItemList.get(i));
            }
        } 

        return FilteredItemList;
    }
    
    
    public List<Item> filter(String Field, String Value) {
        List<Item> FilteredItemList = new ArrayList<>();
        
        List<String> ColumnData = this.getColumn(Field);
        
        for (int i = 0; i < ColumnData.size(); i++) {
            if (Value.equals(ColumnData.get(i))) {
                FilteredItemList.add(this.ItemList.get(i));
            }
        } 

        return FilteredItemList;
    }
    
    public Boolean isEmpty() {
        return this.ItemList.isEmpty();
    }
    
}
