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
public class Payment extends Item {
    private final ItemCollectionFactory CollectionFactory;
    
    public Payment (List<String> FieldNames, List<String> Details) {
        super(FieldNames, Details);
        this.CollectionFactory = new ItemCollectionFactory();
    }
    
    public ItemCollection<PO> getRelatedPOs() {
        return this.CollectionFactory.createRelatedItemCollection("POToPayment", this.getID());
    }
    
    public void addRelatedPO(PO relatedPO) {
        ItemCollection<PO> relatedPOs = this.getRelatedPOs();
        relatedPOs.addItem(relatedPO);
        relatedPOs.UpdateFile();
    }
    
    public void updateRelatedPO(PO relatedPO) {
        ItemCollection<PO> relatedPOs = this.getRelatedPOs();
        relatedPOs.updateItem(relatedPO);
        relatedPOs.UpdateFile();
    }
}
