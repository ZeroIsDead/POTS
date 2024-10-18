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
public class ProductCollection extends ItemCollection {
    public Product createProduct(String name);
    public List<Product> getAllProduct();
    public Product searchProduct(String ID);
    public List<Product> filterProduct(List<String> Fields, List<String> Values);
    public List<Product> sortProduct(String Field);
    public List<Product> sortProductByUrgency();
}
