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
public abstract class Item {
    protected List<String> Fields;
    protected List<String> Details;
    
    public Item (List<String> FieldNames, List<String> Details) {
        this.Fields = FieldNames;
        this.Details = Details;
    }
    
    public String ToString() {
        String Buffer = "";
        
        for (int i = 0; i < this.Fields.size(); i++) {
            Buffer += String.format("%s: %s\n", this.Fields.get(i), this.Details.get(i));
        }
        
        return Buffer;
    }

    public void setDetail(List<String> Details) {
        this.Details = Details;
    }

    public List<String> getDetail() {
        return this.Details;
    }
    
    public void setFields(List<String> FieldNames) {
        this.Fields = FieldNames;
    } 
    
    public List<String> getFields() {
        return this.Fields;
    }
    
}
