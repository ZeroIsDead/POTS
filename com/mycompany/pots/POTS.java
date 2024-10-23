/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pots;

import DataAbstractions.ItemCollectionFactory;
import DataAbstractions.ItemCollection;
import DataAbstractions.PO;
import DataAbstractions.PR;
import DataAbstractions.Product;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author JONATHAN
 */
public class POTS {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        ItemCollectionFactory b = new ItemCollectionFactory();
        ItemCollection<PO> POCollection = b.createItemCollection("PO");
        
//        List<String> Details = new ArrayList<>();
        
//        Details.add("3");
//        Details.add("5");
//        Details.add("1");
//        Details.add("10/10/24");
//        
//        POCollection.createItem(Details);

        
        for (PO newPO : POCollection.getAll()) {
            System.out.println("PO Details \n\n");

            System.out.println(newPO);

            ItemCollection<PR> PRCollection = newPO.getRelatedPRs();

            List<PR> PRList = PRCollection.getAll();
            
            if (PRCollection.isEmpty()) {
                continue;
            }

            System.out.println("PR Details \n\n");
            
            for (PR newPR : PRList) {
                System.out.println(newPR);

                ItemCollection<Product> ProductCollection = newPR.getRelatedProducts();

                if (ProductCollection.isEmpty()) {
                    continue;
                }

                List<Product> ProductList = ProductCollection.getAll();

                System.out.println("Product Details \n\n");


                for (Product a : ProductList) {
                    System.out.println(a);
                }

            }
        }
        
        
        
        
    
    }
    
    
}
