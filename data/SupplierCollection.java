/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.List;

/**
 *
 * @author JONATHAN
 */
public interface SupplierCollection {
    public Supplier createSupplier(String name);
    public List<Supplier> getAllSupplier();
    public Supplier searchSupplier(String ID);
    public List<Supplier> filterSupplier(List<String> Fields, List<String> Values);
    public List<Supplier> sortSupplier(List<String> Fields, List<String> Values);
}
