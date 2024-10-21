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
                return new PO(Details);
            }
            case "PR" -> {
                return new PR(Details);
            }
            case "Payment" -> {
                return new Payment(Details);
            }
            case "Product" -> {
                return new Product(Details);
            }
            case "Purchase" -> {
                return new Purchase(Details);
            }
            case "Sales" -> {
                return new Sales(Details);
            }
            case "Supplier" -> {
                return new Supplier(Details);
            }
            default -> {
                return null;
            }
        }
    }
}
