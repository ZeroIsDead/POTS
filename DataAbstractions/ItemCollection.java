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
            Output += String.format("%d \n\n %s\n\n", count++, CurrentItem);
        } 
        return Output;
    }
    
    public void UpdateFile() {
        List<List<String>> Data = new ArrayList<>(); // Turn Item Into Writable Format
        
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
    
    public int getItemIndex(Item ItemInstance) {
        String ItemInstanceID = ItemInstance.getDetails().get(0);
        
        for (int i = 0; i < this.ItemList.size(); i++) {
            String CurrentItemID = this.ItemList.get(i).getDetails().get(0);
            
            if (CurrentItemID.equals(ItemInstanceID)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int getItemIndex(String ID) {
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
        
        Item newItem = this.Factory.createItem(this.FieldNames, Details, this.Type);
        
        this.ItemList.add(newItem);
        
        return newItem;
    }
    
    public Item createItem(String[] DetailsArray) {
        List<String> Details = Arrays.asList(DetailsArray);
        
        return this.createItem(Details);
    }
    
    public void addItem(Item ItemInstance) {
        if (this.CheckItemInCollection(ItemInstance)) {
            return;
        }
        
        this.ItemList.add(ItemInstance);
    }
    
    public void updateItem(Item ItemInstance) {
        
        int itemIndex = this.getItemIndex(ItemInstance);
        
//        Check if ID is Same But The Details Are Different
        if (itemIndex != -1 && !this.CheckItemInCollection(ItemInstance)) {
            return;
        }
        
        this.ItemList.remove(itemIndex);
        this.ItemList.add(itemIndex, ItemInstance);
    }
    
    public void removeItem(Item ItemInstance) {
        int count = 0;
        for (Item currentItem : this.ItemList) {
            if (currentItem.equals(ItemInstance)) {
                this.ItemList.remove(count);
                return;
            }
            count++;
        }
    }
    
    public void removeItem(String ID) {
        
        int count = 0;
        for (Item currentItem : this.ItemList) {
            List<String> currentItemDetails = currentItem.getDetails();
            
            if (currentItemDetails.get(0).equals(ID)) {
                this.ItemList.remove(count);
                return;
            }
            count++;
        }
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
        for (Item currentItem : this.ItemList) {
            if (currentItem.getDetails().get(0).equals(ID)) {
                return currentItem;
            }
        }
        return null;
    }
    
    public List<Item> filter(List<String> Fields, List<String> Values) {
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
    
    public void extend(ItemCollection otherItemCollection) {
        List<Item> otherItemList = otherItemCollection.ItemList;
        for (Item otherItem : otherItemList) {
            if (!this.CheckItemInCollection(otherItem)) {
                this.ItemList.add(otherItem);
            }
        }
    }
    
    public void extend(List<Item> otherItemList) {
        for (Item otherItem : otherItemList) {
            if (!this.CheckItemInCollection(otherItem)) {
                this.ItemList.add(otherItem);
            }
        }
    }
    
    public Boolean isEmpty() {
        return this.ItemList.isEmpty();
    }
    
}
