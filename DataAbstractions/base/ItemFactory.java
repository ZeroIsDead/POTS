/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions.base;

import DataAbstractions.Item;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class ItemFactory {
    public Item createItem(List<String> RowData, String Type, DataReader reader, DataWriter writer) {
        switch (Type) {
            case "PO" -> {
                return new PO(RowData, Type, reader, writer);
            }
            case "PR" -> {
                return new PR(RowData, Type, reader, writer);
            }
            case "Product" -> {
                return new Product(RowData, Type, reader, writer);
            }
            case "Payment" -> {
                return new Payment(RowData, Type, reader, writer);
            }
            case "Sales" -> {
                return new Sales(RowData, Type, reader, writer);
            }
            case "Supplier" -> {
                return new Supplier(RowData, Type, reader, writer);
            }
            case "User" -> {
                return new User(RowData, Type, reader, writer);
            }
            default -> {
                return null;
            }
        }
    }
}
