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
public abstract class Item {

    public abstract void setDetail(List<String> Details);
    public abstract List<String> getDetail();
    public abstract void deleteContainer();
    
}
