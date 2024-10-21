/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions;

import DataAbstractions.base.DataContainer;
import DataAbstractions.base.DataWriter;
import DataAbstractions.base.FileHandler;
import DataAbstractions.base.ItemFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class ItemCollectionFactory {
    private String parseFilePath(String Type) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        
        return currentWorkingDirectory + "\\src\\main\\java\\data\\" + Type + ".txt";
    }
    
    public ItemCollection createItemCollection(String Type) {
        String FilePath = this.parseFilePath(Type);
        
        DataContainer reader = new FileHandler(FilePath);
        DataWriter writer = new FileHandler(FilePath);
        
        ItemFactory Factory = new ItemFactory(Type, reader, writer);
        
        List<List<String>> ItemDetailList = reader.getData();
        List<String> ItemFields = reader.getFieldName();


        switch (Type) {
            case "PO" -> {
                
                List<PO> POList = new ArrayList<>();
                
                
                for (List<String> RowData : ItemDetailList) {
                    PO newPO = new PO(ItemFields, RowData);
                    
                    POList.add(newPO);
                }
                
                return new ItemCollection<>(POList, Factory, reader);
            }
            case "PR" -> {
                List<PR> PRList = new ArrayList<>();
                
                for (List<String> RowData : ItemDetailList) {
                    PR newPR = new PR(ItemFields, RowData);
                    
                    PRList.add(newPR);
                }
                
                return new ItemCollection<>(PRList, Factory, reader);
            }
            case "Payment" -> {
                List<Payment> PaymentList = new ArrayList<>();
                
                
                for (List<String> RowData : ItemDetailList) {
                    Payment newPayment = new Payment(ItemFields, RowData);
                    
                    PaymentList.add(newPayment);
                }
                
                return new ItemCollection<>(PaymentList, Factory, reader);

            }
            case "Product" -> {
                List<Product> ProductList = new ArrayList<>();
                
                
                for (List<String> RowData : ItemDetailList) {
                    Product newProduct = new Product(ItemFields, RowData);
                    
                    ProductList.add(newProduct);
                }
                
                return new ItemCollection<>(ProductList, Factory, reader);
            }
            case "Purchase" -> {
                List<Purchase> PurchaseList = new ArrayList<>();
                
                
                for (List<String> RowData : ItemDetailList) {
                    Purchase newPurchase = new Purchase(ItemFields, RowData);
                    
                    PurchaseList.add(newPurchase);
                }
                
                return new ItemCollection<>(PurchaseList, Factory, reader);
            }
            case "Sales" -> {
                List<Sales> SalesList = new ArrayList<>();
                
                
                for (List<String> RowData : ItemDetailList) {
                    Sales newSales = new Sales(ItemFields, RowData);
                    
                    SalesList.add(newSales);
                }
                
                return new ItemCollection<>(SalesList, Factory, reader);
            }
            case "Supplier" -> {
                List<Supplier> SupplierList = new ArrayList<>();
                
                
                for (List<String> RowData : ItemDetailList) {
                    Supplier newSupplier = new Supplier(ItemFields, RowData);
                    
                    SupplierList.add(newSupplier);
                }
                
                return new ItemCollection<>(SupplierList, Factory, reader);
            }
            default -> {
                return null;
            }
        }
    }
}
