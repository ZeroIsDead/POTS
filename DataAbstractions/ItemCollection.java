/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions;

import DataAbstractions.base.ItemFactory;
import java.util.List;

/**
 *
 * @author JONATHAN
 * @param <E>
 */
public class ItemCollection<E extends Item> {
    private final List<String> FieldNames;
    private final List<E> ItemList;
    private ItemFactory Factory;
    
    public ItemCollection(List<E> newItemList, ItemFactory newFactory) {
        this.ItemList = newItemList;
        this.Factory = newFactory;
        this.FieldNames = this.Factory.getFieldNames();
    }
    
    @Override
    public String toString() {
        String Output = "";
        
        int count = 1;
        for (E Item : this.ItemList) {
            Output += String.format("%d \n\n %s\n\n", count++, Item);
        } 
        return Output;
    }
    
    public void setFactory(ItemFactory newFactory) {
        this.Factory = newFactory;
    }
    
    public void UpdateFile() {
        this.Factory.write((List<Item>) this.ItemList); // Cast All PO / PR / Product / etc into Item Class
    }
    
    public int getCollectionSize() {
        return this.ItemList.size();
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
    
    public int getItemIndex(E ItemInstance) {
        String ItemInstanceID = ItemInstance.getDetail().get(0);
        
        for (int i = 0; i < this.ItemList.size(); i++) {
            String CurrentItemID = this.ItemList.get(i).getDetail().get(0);
            
            if (CurrentItemID.equals(ItemInstanceID)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int getItemIndex(String ID) {
        for (int i = 0; i < this.ItemList.size(); i++) {
            String CurrentItemID = this.ItemList.get(i).getDetail().get(0);
            
            if (CurrentItemID.equals(ID)) {
                return i;
            }
        }
        
        return -1;
    }
    
    public E createItem(List<String> Details) {
        //Check if item exist in list and If Data Size Matches
        if (Details.size() != this.FieldNames.size() || this.CheckItemInCollection(Details)) {
            return null;
        }
        
        // Type cast Item Into Generic Type
        E newItem = (E) this.Factory.createItem(Details);
        
        this.ItemList.add(newItem);
        
        return newItem;
    }
    
    public void addItem(E ItemInstance) {
        if (this.getItemIndex(ItemInstance) != -1) {
            return;
        }
        
        this.ItemList.add(ItemInstance);
    }
    
    public void updateItem(E ItemInstance) {
        
        int itemIndex = this.getItemIndex(ItemInstance);
        
//        Check if ID is Same But The Details Are Different
        if (itemIndex != -1 && !this.CheckItemInCollection(ItemInstance)) {
            return;
        }
        
        this.ItemList.remove(itemIndex);
        this.ItemList.add(itemIndex, ItemInstance);
    }
    
    public void removeItem(E ItemInstance) {
        int count = 0;
        for (E currentItem : this.ItemList) {
            if (currentItem.equals(ItemInstance)) {
                this.ItemList.remove(count);
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
                return;
            }
            count++;
        }
    }
    
//    public List<String> getColumn(String Field) {
//        return this.reader.getColumn(Field);
//    }
    
    public List<String> getFieldNames() {
        return this.FieldNames;
    }
    
    public List<E> getAll() {
        return this.ItemList;
    }

    public E getItem(String ID) {
        for (E currentItem : this.ItemList) {
            if (currentItem.getDetail().get(0).equals(ID)) {
                return currentItem;
            }
        }
        return null;
    }
    
//    public ItemCollection<E> filter(List<String> Fields, List<String> Values) {
//        List<List<String>> ItemDetailList = this.reader.FitlerData(Fields, Values);
//        List<E> FilteredItemList = new ArrayList<>();
//        
//        for (List<String> ItemDetail : ItemDetailList) {
//            E newItem = (E) this.Factory.createItem(ItemDetail);
//            FilteredItemList.add(newItem);
//        }
//        
//        return new ItemCollection<>(FilteredItemList, this.Factory, this.reader);
//    }   
//    
//    public ItemCollection<E> filter(String Fields, List<String> Values) {
//        List<List<String>> ItemDetailList = this.reader.FitlerData(Fields, Values);
//        List<E> FilteredItemList = new ArrayList<>();
//        
//        for (List<String> ItemDetail : ItemDetailList) {
//            E newItem = (E) this.Factory.createItem(ItemDetail);
//            FilteredItemList.add(newItem);
//        }
//        
//        return new ItemCollection<>(FilteredItemList, this.Factory, this.reader);
//    }
//    
//    
//    public ItemCollection<E> filter(String Fields, String Values) {
//        List<List<String>> ItemDetailList = this.reader.FitlerData(Fields, Values);
//        List<E> FilteredItemList = new ArrayList<>();
//        
//        for (List<String> ItemDetail : ItemDetailList) {
//            E newItem = (E) this.Factory.createItem(ItemDetail);
//            FilteredItemList.add(newItem);
//        }
//        
//        return new ItemCollection<>(FilteredItemList, this.Factory, this.reader);
//    }
    
    public void extend(ItemCollection<E> otherItemCollection) {
        List<E> otherItemList = otherItemCollection.ItemList;
        for (E otherItem : otherItemList) {
            if (!this.CheckItemInCollection(otherItem)) {
                this.ItemList.add(otherItem);
            }
        }
    }
    
    public void extend(List<E> otherItemList) {
        for (E otherItem : otherItemList) {
            if (!this.CheckItemInCollection(otherItem)) {
                this.ItemList.add(otherItem);
            }
        }
    }
    
    public Boolean isEmpty() {
        return this.ItemList.isEmpty();
    }
    
}
