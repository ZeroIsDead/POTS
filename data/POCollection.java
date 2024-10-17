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
public interface POCollection {
    public PO createPO(String name);
    public List<PO> getAllPO();
    public PO searchPO(String ID);
    public List<PO> filterPO(List<String> Fields, List<String> Values);
    public List<PO> sortPO(List<String> Fields, List<String> Values);
}
