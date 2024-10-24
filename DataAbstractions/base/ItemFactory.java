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
    public Item createItem(List<String> ItemFields, List<String> RowData, String Type) {
        switch (Type) {
            case "PO" -> {
                return new PO(ItemFields, RowData, Type);
            }
            case "PR" -> {
                return new PR(ItemFields, RowData, Type);
            }
            case "Product" -> {
                return new Product(ItemFields, RowData, Type);
            }
            case "Payment" -> {
                return new Payment(ItemFields, RowData, Type);
            }
            case "Sales" -> {
                return new Sales(ItemFields, RowData, Type);
            }
            case "Supplier" -> {
                return new Supplier(ItemFields, RowData, Type);
            }
            case "User" -> {
                return new User(ItemFields, RowData, Type);
            }
            default -> {
                return null;
            }
        }
    }
}
