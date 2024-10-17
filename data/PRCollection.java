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
public interface PRCollection {
    public Item createPR(String name);
    public List<PR> getAllPR();
    public PR searchPR(String ID);
    public List<PR> filterPR(List<String> Fields, List<String> Values);
    public List<PR> sortPR(List<String> Fields, List<String> Values);
}
