/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pots;

import DataAbstractions.ItemCollectionFactory;
import DataAbstractions.ItemCollection;
import DataAbstractions.PO;


/**
 *
 * @author JONATHAN
 */
public class POTS {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        ItemCollectionFactory b = new ItemCollectionFactory();
        ItemCollection<PO> c = b.createItemCollection("PO");
        
        System.out.println(c.getItem("1").getDetail());
        
        
//        c.filter(Fields, Values);
        
        
    }
}
