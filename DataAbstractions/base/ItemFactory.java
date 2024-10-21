/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions.base;

import DataAbstractions.Sales;
import DataAbstractions.Product;
import DataAbstractions.Payment;
import DataAbstractions.PO;
import DataAbstractions.Item;
import DataAbstractions.Purchase;
import DataAbstractions.PR;
import DataAbstractions.Supplier;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class ItemFactory {
    private final DataWriter writer;
    private final DataContainer reader;
    private final String Type;
    private final List<String> FieldNames;
    
    public ItemFactory(String Type, DataContainer newReader, DataWriter newWriter) {
        this.Type = Type;
        this.writer = newWriter;
        this.reader = newReader;
        this.FieldNames = this.reader.getFieldName();
    }
    
    public List<String> getFieldNames(){
        return this.FieldNames;
    }
    
    public void write(List<List<String>> Data) {
        this.writer.writeData(Data);
    }
    
    public Item createItem(List<String> Details) {
        switch (this.Type) {
            case "PO" -> {
                return new PO(this.FieldNames, Details);
            }
            case "PR" -> {
                return new PR(this.FieldNames, Details);
            }
            case "Payment" -> {
                return new Payment(this.FieldNames, Details);
            }
            case "Product" -> {
                return new Product(this.FieldNames, Details);
            }
            case "Purchase" -> {
                return new Purchase(this.FieldNames, Details);
            }
            case "Sales" -> {
                return new Sales(this.FieldNames, Details);
            }
            case "Supplier" -> {
                return new Supplier(this.FieldNames, Details);
            }
            default -> {
                return null;
            }
        }
    }
}
