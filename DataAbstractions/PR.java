/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions;

import java.util.List;

/**
 *
 * @author JONATHAN
 */
public class PR extends Item {
    private final ItemCollectionFactory CollectionFactory;
    
    
    public PR (List<String> FieldNames, List<String> Details) {
        super(FieldNames, Details);
        this.CollectionFactory = new ItemCollectionFactory();
    }
    
    public ItemCollection<Product> getRelatedProducts() {
        return this.CollectionFactory.createRelatedItemCollection("ProductToPR", this.getID());
    }
    
    public void addRelatedProduct(Product relatedProduct) {
        ItemCollection<Product> relatedProducts = this.getRelatedProducts();
        relatedProducts.addItem(relatedProduct);
        relatedProducts.UpdateFile();
    }
    
    public void updateRelatedProduct(Product relatedProduct) {
        ItemCollection<Product> relatedProducts = this.getRelatedProducts();
        relatedProducts.updateItem(relatedProduct);
        relatedProducts.UpdateFile();
    }
}
