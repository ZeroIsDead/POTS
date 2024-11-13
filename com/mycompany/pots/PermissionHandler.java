/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pots;

import DataAbstractions.Item;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class PermissionHandler {
    private final HashMap<String, HashMap<String, List<String>>> Permissions = new HashMap<>();
    
    PermissionHandler() {
        HashMap<String, List<String>> IMPermissions = new HashMap<>();
        
        IMPermissions.put("Product", List.of("Add", "Edit", "Delete")); // Full Access to Item
        IMPermissions.put("Supplier", List.of("Add", "Edit", "Delete")); // Full Access to Supplier
        IMPermissions.put("Stock Level", List.of("View", "Add", "Edit", "Delete")); // View and Update Stock Level
        
        HashMap<String, List<String>> SMPermissions = new HashMap<>();
        
        SMPermissions.put("Product", List.of("View")); // View Product
        SMPermissions.put("Sales", List.of("View", "Add", "Edit", "Delete")); // Full Access to Sales
        SMPermissions.put("Sales Report", List.of("Generate")); // Generate Sales Report
        SMPermissions.put("Stock Level", List.of("View")); // View Stock Level
        SMPermissions.put("Purchase Requisition", List.of("Add")); //  View PR
        SMPermissions.put("Purhcase Order", List.of("View")); // View PO
        
        HashMap<String, List<String>> PMPermissions = new HashMap<>();
        
        PMPermissions.put("Product", List.of("View")); // View Products
        PMPermissions.put("Supplier", List.of("View")); // View Supplier
        PMPermissions.put("Purchase Requisition", List.of("Add", "View")); // Create or View PR
        PMPermissions.put("Purchase Order", List.of("View", "Add", "Edit", "Delete")); //  Generate and View PO
        
        HashMap<String, List<String>> FMPermissions = new HashMap<>();
        
        FMPermissions.put("Purchase Order", List.of("View", "Edit")); // Approve / Reject , Make Payment
        FMPermissions.put("Stock Level", List.of("View")); // Check Stock Status
        FMPermissions.put("Payment", List.of("Add", "View", "Edit")); // Make Payment, View Payment, Status of Delivery
        
        HashMap<String, List<String>> AdminPermissions = new HashMap<>();
        
//        AdminPermissions.put("");
        
        Permissions.put("IM", IMPermissions);
        Permissions.put("PM", PMPermissions);
        Permissions.put("SM", SMPermissions);
        Permissions.put("FM", FMPermissions);
        Permissions.put("Admin", AdminPermissions);
    }
    
    public Boolean HasPermission(Item User, String Resource, String Action) {
        if (!User.getType().equals("User")) {
            return false;
        }
        
        HashMap<String, List<String>> UserPermissions = this.Permissions.get(User.getFieldValue("Role"));
        
        if (UserPermissions == null || !UserPermissions.containsKey(Resource)) {
            return null;
        }
        
        List<String> UserResourcePermissions = UserPermissions.get(Resource);
        
        return UserResourcePermissions.contains(Action);
    }
    
    public List<String> GetPermissions(Item User, String Resource) {
        if (!User.getType().equals("User")) {return null;}
        
        HashMap<String, List<String>> UserPermissions = this.Permissions.get(User.getFieldValue("Role"));
        
        if (UserPermissions == null || !UserPermissions.containsKey(Resource)) {
            return null;
        }
        
        return UserPermissions.get(Resource);
    }
    
    public HashMap<String, List<String>> GetPermissions(Item User) {
        if (!User.getType().equals("User")) {return null;}
        
        return this.Permissions.get(User.getFieldValue("Role"));
    }
}
