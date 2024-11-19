/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pots;

import DataAbstractions.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class CaseHandler {

    public static void main(String[] args) {
//    Handles all view / JFrame logic - MAIN class / file

//    Login Page
//    Generate Menu For All Functionality From Permissions

        String ID = "peter";

//    Init User / Get User
        ItemCollectionFactory Factory = new ItemCollectionFactory();
        ItemCollection UserCollection = Factory.createItemCollection("User");
        
        Item User = UserCollection.getItem(ID);
        
        
        if (User == null) {
            return;
        }
        
        String[] UDetails = User.getDetails();
        
        UDetails[0] = "1";
        
        System.out.println(Arrays.toString(UDetails));
        
        System.out.println(Arrays.toString(User.getDetails()));
        
        System.out.println(Arrays.toString(UserCollection.getItem(ID).getDetails()));
        
        System.out.println(User.getType());
        
//    Use PermissionHandler 
        PermissionHandler PHandler = new PermissionHandler();

        List<String> Resources = List.of("Purchase Order", "Purchase Requisition", "Payment", "Sales", "Product", "Supplier", "User");
        
        System.out.println(User);
        
        for (int n = 0; n < Resources.size(); n++) {
            
            List<String> ResourcePermissions = PHandler.GetPermissions(User, Resources.get(n));
            
            if (ResourcePermissions == null) {
                continue;
            }
        
            System.out.println(n + " " + Resources.get(n));

            
            for (int i = 0; i < ResourcePermissions.size(); i++) {
                System.out.println("\t" + i + " " + ResourcePermissions.get(i));
            }
        }
           
    }
}

