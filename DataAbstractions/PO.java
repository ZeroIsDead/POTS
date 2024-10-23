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
public class PO extends Item {
    private final ItemCollectionFactory CollectionFactory;
    
    public PO (List<String> FieldNames, List<String> Details) {
        super(FieldNames, Details);
        this.CollectionFactory = new ItemCollectionFactory();
    }
    
    public ItemCollection<PR> getRelatedPRs() {
        return this.CollectionFactory.createRelatedItemCollection("PRToPO", this.getID());
    }
    
    public void addRelatedPR(PR relatedPR) {
        ItemCollection<PR> relatedPRs = this.getRelatedPRs();
        relatedPRs.addItem(relatedPR);
        relatedPRs.UpdateFile();
    }
    
    public void updateRelatedPR(PR relatedPR) {
        ItemCollection<PR> relatedPRs = this.getRelatedPRs();
        relatedPRs.updateItem(relatedPR);
        relatedPRs.UpdateFile();
    }
    
}
