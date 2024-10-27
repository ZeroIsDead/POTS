/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions;

//import DataAbstractions.base.DataContainer;
//import DataAbstractions.base.DataWriter;
//import DataAbstractions.base.FileHandler;
//import java.util.ArrayList;
import DataAbstractions.base.DataContainer;
import DataAbstractions.base.DataWriter;
import java.util.List;

/**
 *
 * @author JONATHAN
 */
public abstract class Item {
    protected List<String> Fields;
    protected List<String> Details;
    protected String Type;
    protected DataWriter writer;
    protected DataContainer reader;
    
    public Item (List<String> Details, String Type, DataContainer reader, DataWriter writer) {
        this.Type = Type;
        this.reader = reader;
        this.writer = writer;
        this.Fields = this.reader.getFieldName();
        this.Details = Details;
    }
    
    @Override
    public String toString() {
        String Buffer = "";
        
        for (int i = 0; i < this.Fields.size() && i < this.Details.size(); i++) {
            Buffer += String.format("%s: %s\n", this.Fields.get(i), this.Details.get(i));
        }
        
        return Buffer;
    }
    
    public String getID() {
        if (this.Details.isEmpty()) {
            return "";
        }
        
        return this.Details.get(0);
    }
    
    public String getType() {
        return this.Type;
    }

    public List<String> getFields() {
        return this.Fields;
    }
    
    public List<String> getDetails() {
        return this.Details;
    }

    public void setDetails(List<String> newDetails) {
        this.Details = newDetails;
    }
    
    public abstract List<Item> getUpwardsRelatedItems(String Type); // Get which PO the PR is in
    
    public abstract List<Item> getDownwardsRelatedItems(String Type); // Get List of PR of a PO

    public abstract void addRelatedItem(Item newItem);

    public abstract void deleteRelatedItem(Item newItem);
    
}
