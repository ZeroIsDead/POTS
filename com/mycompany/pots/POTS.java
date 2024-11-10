/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pots;

import DataAbstractions.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class POTS {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        ItemCollectionFactory Factory = new ItemCollectionFactory();
        ItemCollection POCollection = Factory.createItemCollection("PO");
        
        String[] DetailsArray = {"3", "6", "1", "10/10/24"};
        List<String> Details = Arrays.asList(DetailsArray);
        
        System.out.println(Details);
        
//        Details.add("3");
//        Details.add("5");
//        Details.add("1");
//        Details.add("10/10/24");

//        
        Item PO = POCollection.getItem("3");
        
//        String[] NewDetailsArray = {"3", "6", "1", "10/10/25"};
//        List<String> NewDetails = Arrays.asList(NewDetailsArray);
//
//        
//        PO.setDetails(NewDetails);
//        
//        POCollection.updateItem(PO);
        
        PO.setFieldValue("Date", "10/10/25");
        
        ItemCollection ProductCollection = Factory.createItemCollection("Product");
        
        List<Item> SortedList = ProductCollection.getSortedItems((a, b) -> {
            return (Integer.parseInt(a.getFieldValue("Quantity")) / Integer.parseInt(a.getFieldValue("Threshold"))) - (Integer.parseInt(b.getFieldValue("Quantity")) / Integer.parseInt(b.getFieldValue("Threshold"))) ;
        });
        
        for (Item SortedProduct : SortedList) {
            System.out.println(SortedProduct);
        }
        
        POCollection.updateItem(PO);
        
        
//        POCollection
        
        String[] Fields = {"UserID", "Date"};
        String[] Values = {"5", "10/10/24"};
        
        List<String> FieldList = Arrays.asList(Fields);
        List<String> ValuesList = Arrays.asList(Values);
        
//        System.out.println(POCollection + "adn");
        
        List<Item> PoList = POCollection.filter(FieldList, ValuesList);
        
        POCollection.filter("POID", ValuesList);
        
        ItemCollection PRCollection = Factory.createItemCollection("PR");
        
        List<String> DetailsList = List.of("5","1","1","1");
        
        Item PR = PRCollection.createItem(DetailsList);
        
        if (PR == null) {
            PR = PRCollection.getItem(DetailsList.get(0));
        }
        
        PO.addRelatedItem(PR);
        PO.deleteRelatedItem(PR);
        
        for (Item FilteredPO : PoList) {
            System.out.println("FUCK");
            System.out.println(FilteredPO);
        }
        
//        for (Item newPO : POCollection.getAll()) {
//            System.out.println("PO Details \n\n");
//            
//            System.out.println(newPO);
//            
//            List<Item> PRCollection = newPO.getDownwardsRelatedItems("PR");
//
//            if (PRCollection == null) {
//                continue;
//            }
//
//            System.out.println("PR Details \n\n");
//            
//            for (Item newPR : PRCollection) {
//                System.out.println(newPR);
//                
//                List<Item> ProductCollection = newPR.getDownwardsRelatedItems("Product");
//
//                if (ProductCollection == null) {
//                    continue;
//                }
//
//                System.out.println("Product Details \n\n");
//
//
//                for (Item a : ProductCollection) {
//                    System.out.println(a);
//                }
//
//            }
//        }
        
        
        
        
    
    }
    
    
}
