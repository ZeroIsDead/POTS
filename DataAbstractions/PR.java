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
private List<String> Details;
    
    public PR (List<String> Details) {
        this.Details = Details;
    }

    @Override
    public void setDetail(List<String> Details) {
        this.Details = Details;
    }

    @Override
    public List<String> getDetail() {
        return this.Details;
    }

}
