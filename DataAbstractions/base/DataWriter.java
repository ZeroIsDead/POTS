/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DataAbstractions.base;

import java.util.List;

/**
 *
 * @author JONATHAN
 */
public interface DataWriter {
    public void setFileName(String filePath);
    public String getFileName();
    public void writeData(List<List<String>> Data);
    public void appendData(List<String> Data);
    public void updateData(List<String> Data);
    public void deleteData(String ID);
    public void updateCompositeData(List<String> Data, List<String> Keys);
    public void deleteCompositeData(List<String> Keys);
}
