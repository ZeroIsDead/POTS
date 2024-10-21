/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions.base;


import java.util.List;

/**
 *
 * @author JONATHAN
 */
public interface DataContainer {
    public void setFilePath(String filePath);
    public String getFilePath();
    public List<List<String>> getData();
    public List<String> getRow(String key);
    public List<String> getColumn(String Field);
    public List<String> getFieldName();
    public List<List<String>> FitlerData(List<String> Field, List<String> Value);
}
