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
public class SalesCollection extends ItemCollection{
    public Sales createSales(String name);
    public List<Sales> getAllSales();
    public Sales searchSales(String ID);
    public List<Sales> filterSales(List<String> Fields, List<String> Values);
    public List<Sales> sortSales(String Field);
}
