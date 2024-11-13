/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions;

import DataAbstractions.base.DataWriter;
import DataAbstractions.base.FileHandler;
import DataAbstractions.base.ItemFactory;
import java.util.ArrayList;
import java.util.List;
import DataAbstractions.base.DataReader;

/**
 *
 * @author JONATHAN
 */
public class ItemCollectionFactory {
    public ItemCollection createItemCollection(String Type) {
        DataReader reader = new FileHandler(Type);
        DataWriter writer = new FileHandler(Type);
        
        List<List<String>> ItemDetailList = reader.getData();
        
        List<Item> ItemList = new ArrayList<>();
        
        ItemFactory Factory = new ItemFactory();
        
        for (List<String> RowData : ItemDetailList) {
            Item newItem = Factory.createItem(RowData, Type);

            ItemList.add(newItem);
        }
        
        return new ItemCollection(ItemList, reader, writer, Factory, Type);
    }
}
    
