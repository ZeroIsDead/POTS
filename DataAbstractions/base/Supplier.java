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
public class Supplier extends Item {

    public Supplier(List<String> Details, String Type, DataContainer reader, DataWriter writer) {
        super(Details, Type, reader, writer);
    }

    @Override
    public List<Item> getUpwardsRelatedItems(String Type) {
        return null;
    }

    @Override
    public List<Item> getDownwardsRelatedItems(String Type) {
        switch (Type) {
            case "Product" -> {
                return this.getProductRelatedToSupplier();
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
    
    private List<Item> getProductRelatedToSupplier() {
        String ID = this.getID();
        
//        Reading and Writing To The Relationship File
        this.reader.setFilePath("Product");
        
//        Get All Product/PR/PO Rows Related To The PR/PO/Payment
        List<List<String>> ItemDetailList = this.reader.FitlerData("SupplierID", ID);
        
        if (ItemDetailList.isEmpty()) {
            return null;
        }
        
        List<Item> ItemList = new ArrayList<>();
        
        for (int i = 0; i < ItemDetailList.size(); i++) {
            List<String> RowData = ItemDetailList.get(i);

            ItemFactory Factory = new ItemFactory();

            Item newItem = Factory.createItem(RowData, "Product", this.reader, this.writer);
            ItemList.add(newItem);
        }

        return ItemList;
    }

}
