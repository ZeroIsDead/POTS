/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DataAbstractions.Item;
import DataAbstractions.ItemCollection;
import DataAbstractions.ItemCollectionFactory;
import com.mycompany.pots.PermissionHandler;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
class BigPane extends JPanel {
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }
}

//    panel.preferredSize();

public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     * @param User
     */
    public Menu(Item User) {
        
        setTitle("Main Menu");
        
        setContentPane(new BigPane());
        
//    Use PermissionHandler 
        PermissionHandler PHandler = new PermissionHandler();

        JLabel Label = new JLabel("Main Menu");
        Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        add(Label);
        
        System.out.println(User);
        
        List<String> Resources = PHandler.GetPermittedResources(User);
        
        for (String Resource : Resources) {
            System.out.println(Resource);
            JButton button = new JButton(Resource);
            
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    switch (Resource) {
                        case "Purchase Order" -> {
                            setVisible(false);
                            
                            PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User, "Purchase Order");
                            new PurchaseOrder(User, permissionList).setVisible(true);
                            
                        }
                        case "Purchase Requisition" -> {
                            setVisible(false);
                            
                            PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User, "Purchase Requisition");
                            new PurchaseRequisition(User, permissionList).setVisible(true);
                        }
                        case "Product" -> {
                            setVisible(false);
                            
                            PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User, "Product");
                            new Product(User, permissionList).setVisible(true);
                        }
                        case "Payment" -> {
                            setVisible(false);
                            PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User,"Payment");
                            new Payment(User, permissionList).setVisible(true);
                        }
                        case "Sales" -> {
                            setVisible(false);
                            PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User, "Sales");
                            new Sales(User, permissionList).setVisible(true);
                        }
                        case "Supplier" -> {
                            setVisible(false);
                            PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User, "Supplier");
                            new Sales(User, permissionList).setVisible(true);
                        }
                        case "User" -> {
                            setVisible(false);
                            new Register(User).setVisible(true);
                        }
                        case "Sales Report" -> {
                            setVisible(false);
                            PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User, "Sales Report");
                            new Sales(User, permissionList).setVisible(true);
                        }
                        case "Stock Level" -> {
                            setVisible(false);
                             PermissionHandler permission = new PermissionHandler();
                            
                            List<String> permissionList = permission.GetPermissions(User, "Sales Report");
                            new Sales(User, permissionList).setVisible(true);
                        }
                        default -> {
                        }
                    }
                    
                }
            });
            
            add(button);
        }
        
       JButton LogOutButton = new JButton("Log Out");
       
       LogOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    setVisible(false);
                    new Login().setVisible(true);
               }
       });
               
       add(LogOutButton);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        
        setLayout(new GridLayout(Resources.size() + 2, 1, 20, 20)); // No. Button + 1 Rows 1 Column 20 H Gap 20 V Gap
        
        

        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ItemCollectionFactory Factory = new ItemCollectionFactory();
                ItemCollection UserCollection = Factory.createItemCollection("User");
                
                String ID = "peter";
                
                Item User = UserCollection.getItem(ID);
    
                if (User == null) {
                    return;
                }
            
                System.out.println(User.getType());
                new Menu(User).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
