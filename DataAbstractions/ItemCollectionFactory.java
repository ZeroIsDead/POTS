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
    
    private ItemFactory createItemFactory(String Type) {
        String FilePath = this.parseFilePath(Type);
        
        DataWriter writer = new FileHandler(FilePath);
        DataContainer reader = new FileHandler(FilePath);
        
        return new ItemFactory(Type, reader, writer);
    }
    
    public ItemCollection createRelatedItemCollection(String Type, String ID) {
        
//        Create A New Factory Reading and Writing To The Relationship File Instead
        String FilePath = this.parseFilePath(Type);
        
        DataContainer reader = new FileHandler(FilePath);
        
//        Get Wanted Field and Valid Data to Help Filter Out All Relationships For - Product To PR / Product List
        String RelationshipWantedFields = reader.getFieldName().get(0);

//        Get All Product/PR/PO ID Related To The PR/PO/Payment
        List<List<String>> Relationship = reader.FitlerData(RelationshipWantedFields, ID);
        
        ItemFactory newFactory = this.createItemFactory(Type);
        
        if (Relationship.isEmpty()) {
            return null;
        }
        
        String ItemWantedFields = reader.getFieldName().get(1); // POID / PRID / ProductID
        List<String> RelatedItemIDList = new ArrayList<>();
        
        for (List<String> r : Relationship) {
            RelatedItemIDList.add(r.get(1)); // get ID of All Related Items 
        }
//        Get All Product/PR/PO Related To The PR/PO/Payment

        switch (Type) {
            case "ProductToPR", "ProductToSales" -> {
//                Get The Quantity Of Product Sold/Bought With Its Index
                List<String> ItemQuantities = reader.getColumn("Quantity");
                int WantedFieldIndex = reader.getFieldName().indexOf("Quantity");
                
                
//                Read Product File And Get All Related Products To PR/Sales
                String ProductFilePath = this.parseFilePath("Product");
                
                reader.setFilePath(ProductFilePath);
                
                List<List<String>> ProductDetailList = reader.FitlerData(ItemWantedFields, RelatedItemIDList);
                
                ItemFactory Factory = this.createItemFactory("Product");
                
                List<Product> ProductList = new ArrayList<>();
                
                for (int i = 0; i < ProductDetailList.size(); i++) {
                    List<String> RowData = ProductDetailList.get(i);
                    
//                    Replace The Quantity Of The Product In Inventory To The Quantity Sold/Bought
                    RowData.remove(WantedFieldIndex);
                    RowData.add(WantedFieldIndex, ItemQuantities.get(i));
                    
                    Product newProduct = (Product) Factory.createItem(RowData);
                    
                    System.out.println(newProduct);
                    
                    ProductList.add(newProduct);
                }
                
                return new ItemCollection<>(ProductList, newFactory);
            }
            case "PRToPO" -> {
                String PRFilePath = this.parseFilePath("PR");
                
                reader.setFilePath(PRFilePath);
                
                List<List<String>> PRDetailList = reader.FitlerData(ItemWantedFields, RelatedItemIDList);
                
                ItemFactory Factory = this.createItemFactory("PR");
                
                List<PR> PRList = new ArrayList<>();
                
                for (List<String> RowData : PRDetailList) {
                    PR newPR = (PR) Factory.createItem(RowData);
                    
                    System.out.println(newPR);
                    
                    PRList.add((PR) Factory.createItem(RowData));
                }
                
                return new ItemCollection<>(PRList, newFactory);

            }
            case "POToPayment" -> {
                String POFilePath = this.parseFilePath("PO");
                
                reader.setFilePath(POFilePath);
                
                List<List<String>> PODetailList = reader.FitlerData(ItemWantedFields, RelatedItemIDList);
                
                ItemFactory Factory = this.createItemFactory("PO");
                
                List<PO> POList = new ArrayList<>();
                
                for (List<String> RowData : PODetailList) {
                    PO newPO = (PO) Factory.createItem(RowData);
                    
                    System.out.println(newPO);
                    
                    POList.add(newPO);
                }
                
                return new ItemCollection<>(POList, newFactory);
            } 
            default -> {
                return null;
            }
        }
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
                
                return new ItemCollection<>(POList, Factory);
            }
            case "PR" -> {
                List<PR> PRList = new ArrayList<>();
                
                for (List<String> RowData : ItemDetailList) {
                    PR newPR = new PR(ItemFields, RowData);
                    
                    PRList.add(newPR);
                }
                
                return new ItemCollection<>(PRList, Factory);
            }
            case "Payment" -> {
                List<Payment> PaymentList = new ArrayList<>();
                
                for (List<String> RowData : ItemDetailList) {
                    Payment newPayment = new Payment(ItemFields, RowData);
                    
                    PaymentList.add(newPayment);
                }
                
                return new ItemCollection<>(PaymentList, Factory);

            }
            case "Product" -> {
                List<Product> ProductList = new ArrayList<>();
                
                for (List<String> RowData : ItemDetailList) {
                    Product newProduct = new Product(ItemFields, RowData);
                    
                    ProductList.add(newProduct);
                }
                
                return new ItemCollection<>(ProductList, Factory);
            }
            case "Sales" -> {
                List<Sales> SalesList = new ArrayList<>();
                
                for (List<String> RowData : ItemDetailList) {
                    Sales newSales = new Sales(ItemFields, RowData);
                    
                    SalesList.add(newSales);
                }
                
                return new ItemCollection<>(SalesList, Factory);
            }
            case "Supplier" -> {
                List<Supplier> SupplierList = new ArrayList<>();
                
                
                for (List<String> RowData : ItemDetailList) {
                    Supplier newSupplier = new Supplier(ItemFields, RowData);
                    
                    SupplierList.add(newSupplier);
                }
                
                return new ItemCollection<>(SupplierList, Factory);
            }
            default -> {
                return null;
            }
        }
    }
}
    
