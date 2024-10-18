/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JONATHAN
 * @param <Type>
 */
public abstract class ItemCollection<Type> {
    DataWriter writer;
    DataContainer container;
    
    public ItemCollection() {
        this.writer = new FileHandler();
        this.container = new FileHandler();
    }
    
//   PO

    public abstract Type create(List<String> Details);

    public List<Type> getAll() {
        List<Type> ItemList = new ArrayList<>();
        
        List<List<String>> POData = this.container.getData();
        
        for (List<String> RowData : POData) {
            Type newItem = this.create(RowData);
            ItemList.add(newItem);
        }
        
        return ItemList;
    }

    public Type search(String ID) {
        List<Type> ItemList = this.getAll();
        for (Type Item : ItemList) {
            if (true) {
                return Item;
            }
        }
        return null;
    }

    public List<Type> filter(List<String> Fields, List<String> Values) {
        List<List<String>> PODetailList = this.container.FitlerData(Fields, Values);
        
        List<Type> ItemList = new ArrayList<>();
        
        for (List<String> PODetail : PODetailList) {
            Type newItem = this.create(PODetail);
            ItemList.add(newItem);
        }
        
        return ItemList;
    }        
}
