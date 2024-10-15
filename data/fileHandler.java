/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import com.mycompany.pots.dataWriter;
import com.mycompany.pots.dataContainer;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author JONATHAN
 */
public class fileHandler implements dataWriter, dataContainer {
    private String filePath;
    private File file;
    private FileWriter writer;
    private Scanner reader;
    
    public fileHandler() {}
    
    public fileHandler(String filePath) {
        this.setFile(filePath);
    }
    
    private List<String> parseData() { 
    }

    @Override
    public final void setFile(String filePath) {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            this.reader = new Scanner(this.file);
            this.writer = new FileWriter(filePath);
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
    }

    @Override
    public void writeData(List<List<String>> Data) {
        
    }

    @Override
    public void appendData(List<List<String>> Data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateData(List<String> Data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteData(String ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateCompositeData(List<String> Data, List<String> Keys) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteCompositeData(List<String> Keys) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<List<String>> getData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> getRow(String key) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> getColumn(String Field) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> getFieldName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<List<String>> FitlerData(String Field, String Value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<String> queryFieldStrict(String key, String Field) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
