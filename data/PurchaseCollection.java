/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data;

import java.util.List;

/**
 *
 * @author JONATHAN
 */
public interface PurchaseCollection {
    public Purchase createPurchase(String name);
    public List<Purchase> getAllPurchase();
    public Purchase searchPurchase(String ID);
    public List<Purchase> filterPurchase(List<String> Fields, List<String> Values);
    public List<Purchase> sortPurchase(List<String> Fields, List<String> Values);
}
