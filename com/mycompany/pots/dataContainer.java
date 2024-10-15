/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pots;


import java.util.List;

/**
 *
 * @author JONATHAN
 */
public interface dataContainer {
    public void setFile(String filePath);
    public List<List<String>> getData();
    public List<String> getRow(String key);
    public List<String> getColumn(String Field);
    public List<String> getFieldName();
    public List<List<String>> FitlerData(String Field, String Value);
    public List<String> queryFieldStrict(String key, String Field);
}
