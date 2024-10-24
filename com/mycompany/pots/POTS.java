/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pots;

import DataAbstractions.Item;
import DataAbstractions.ItemCollectionFactory;
import DataAbstractions.ItemCollection;
import DataAbstractions.base.PO;
import DataAbstractions.base.PR;
import DataAbstractions.base.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author JONATHAN
 */
public class POTS {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        ItemCollectionFactory b = new ItemCollectionFactory();
        ItemCollection POCollection = b.createItemCollection("PO");
        
        
//        String[] DetailsArray = {"3", "5", "1", "10/10/24"};
//        List<String> Details = Arrays.asList(DetailsArray);
//        
//        System.out.println(Details);
        
//        Details.add("3");
//        Details.add("5");
//        Details.add("1");
//        Details.add("10/10/24");
//        
//        POCollection.createItem(Details);

        
        for (Item newPO : POCollection.getAll()) {
            System.out.println("PO Details \n\n");

            System.out.println(newPO);
            
            List<Item> PRCollection = newPO.getDownwardsRelatedItems("PR");

            if (PRCollection == null) {
                continue;
            }

            System.out.println("PR Details \n\n");
            
            for (Item newPR : PRCollection) {
                System.out.println(newPR);
                
                List<Item> ProductCollection = newPR.getDownwardsRelatedItems("Product");

                if (ProductCollection == null) {
                    continue;
                }

                System.out.println("Product Details \n\n");


                for (Item a : ProductCollection) {
                    System.out.println(a);
                }

            }
        }
        
        
        
        
    
    }
    
    
}
