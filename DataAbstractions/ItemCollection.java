/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions;

import DataAbstractions.base.DataContainer;
import DataAbstractions.base.ItemFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JONATHAN
 * @param <E>
 */
public class ItemCollection<E extends Item> {
    private final List<String> FieldNames;
    private final List<E> ItemList;
    private final ItemFactory Factory;
    private final DataContainer reader;
    
    public ItemCollection(List<E> newItemList, ItemFactory newFactory, DataContainer newReader) {
        this.ItemList = newItemList;
        this.Factory = newFactory;
        this.reader = newReader;
        this.FieldNames = this.Factory.getFieldNames();
    }
    
    private List<List<String>> parseItemIntoWritableFormat() {
        List<List<String>> allDetails = new ArrayList<>();
        
        for (E currentItem : this.ItemList) {
            allDetails.add(currentItem.getDetail());
        }
        return allDetails;
    }
    
    private void UpdateFile() {
        List<List<String>> WritableItemList = this.parseItemIntoWritableFormat();
        this.Factory.write(WritableItemList);
    }
    
    public Boolean CheckItemInCollection(E ItemInstance) {
        for (E CurrentItem : this.ItemList) {
            if (CurrentItem.getDetail().equals(ItemInstance.getDetail())) {
                return true;
            }
        }
        
        return false;
    }
    
    public Boolean CheckItemInCollection(List<String> Details) {
        for (E CurrentItem : this.ItemList) {
            if (CurrentItem.getDetail().equals(Details)) {
                return true;
            }
        }
        
        return false;
    }
    
    public E createItem(List<String> Details) {
        //Check if item exist in list and If Data Size Matches
        if (Details.size() != this.FieldNames.size() || this.CheckItemInCollection(Details)) {
            return null;
        }
        
        // Type cast Item Into Generic Type
        E newItem = (E) this.Factory.createItem(Details);
        
        this.ItemList.add(newItem);
        this.UpdateFile();
        
        return newItem;
    }
    
    public void addItem(E ItemInstance) {
        if (this.CheckItemInCollection(ItemInstance)) {
            return;
        }
        
        this.ItemList.add(ItemInstance);
        this.UpdateFile();
    }
    
    public void removeItem(E ItemInstance) {
        int count = 0;
        for (E currentItem : this.ItemList) {
            if (currentItem.equals(ItemInstance)) {
                this.ItemList.remove(count);
                this.UpdateFile();
                return;
            }
            count++;
        }
    }
    
    public void removeItem(String ID) {
        
        int count = 0;
        for (E currentItem : this.ItemList) {
            List<String> currentItemDetails = currentItem.getDetail();
            
            if (currentItemDetails.get(0).equals(ID)) {
                this.ItemList.remove(count);
                this.UpdateFile();
                return;
            }
            count++;
        }
    }
    
    public List<String> getColumn(String Field) {
        return this.reader.getColumn(Field);
    }
    
    public List<String> getFieldNames() {
        return this.FieldNames;
    }

    public E getItem(String ID) {
        for (E currentItem : this.ItemList) {
            if (currentItem.getDetail().get(0).equals(ID)) {
                return currentItem;
            }
        }
        return null;
    }
    
    public ItemCollection<E> filter(List<String> Fields, List<String> Values) {
        List<List<String>> PODetailList = this.reader.FitlerData(Fields, Values);
        List<E> FilteredItemList = new ArrayList<>();
        
        for (List<String> PODetail : PODetailList) {
            E newItem = (E) this.Factory.createItem(PODetail);
            FilteredItemList.add(newItem);
        }
        
        return new ItemCollection<>(FilteredItemList, this.Factory, this.reader);
    }        
    
    public void extend(ItemCollection<E> otherItemCollection) {
        List<E> otherItemList = otherItemCollection.ItemList;
        for (E otherItem : otherItemList) {
            this.ItemList.add(otherItem);
        }
    }
}
