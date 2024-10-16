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
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author JONATHAN
 */
public class fileHandler implements dataWriter, dataContainer {
    private String filePath;
    private File file;
    private FileWriter writer;
    private Scanner reader;
    private List<List<String>> data;
    private List<String> fields;
    
    public fileHandler() {
        this.data = new ArrayList<>();
    }
    
    public fileHandler(String filePath) {
        this.data = new ArrayList<>();
        this.setFile(filePath);
    }
    
    private String parseData(List<String> rowData) { 
        String parsedRowData = "";

        for (String currentData : rowData) {
            parsedRowData += currentData + ";";
        }
        
        return parsedRowData;
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
        
        this.getFieldAndData();
    }

    @Override
    public void writeData(List<List<String>> Data) {
        try {
            String writeBuffer = new String();
            for (List<String> rowData : Data) {
               writeBuffer += this.parseData(rowData) + "\n";
            }
            
            this.writer.write(writeBuffer);
            
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @Override
    public void appendData(List<List<String>> Data) {
        try {
            String parsedRowData = new String();
            for (List<String> rowData : Data) {
                parsedRowData = this.parseData(rowData) + "\n";
            }
            
            this.writer.append(parsedRowData);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void updateData(List<String> Data) {
        int iterator = 0;
        for (List<String> rowData : this.data) {
            if (rowData.getFirst().equals(Data.getFirst())) {
                this.data.remove(iterator);
                this.data.add(iterator, rowData);
            }
            
            iterator++;
        }
        
        this.writeData(this.data);
    }

    @Override
    public void deleteData(String ID) {
        int iterator = 0;
        for (List<String> rowData : this.data) {
            if (rowData.getFirst().equals(ID)) {
                this.data.remove(iterator);
            }
            
            iterator++;
        }
        
        this.writeData(this.data);
    }

    @Override
    public void updateCompositeData(List<String> Data, List<String> Keys) {
        int iterator = 0;
        for (List<String> rowData : this.data) {
            int numKeysFound = 0;
            for (String currentData : rowData) {
                for (String Key : Keys) {
                    if (currentData.equals(Key)) {
                        numKeysFound++;
                    }
                    
                    if (numKeysFound == Keys.size()) {
                        this.data.remove(iterator);
                        this.data.add(iterator, Data);
                    }

                }
            }
            
            
            iterator++;
        }
    }

    @Override
    public void deleteCompositeData(List<String> Keys) {
        int iterator = 0;
        for (List<String> rowData : this.data) {
            int numKeysFound = 0;
            for (String currentData : rowData) {
                for (String Key : Keys) {
                    if (currentData.equals(Key)) {
                        numKeysFound++;
                    }
                    
                    if (numKeysFound == Keys.size()) {
                        this.data.remove(iterator);
                    }
                }
            }
            
            
            iterator++;
        }
    }
    
    private void getFieldAndData() {
        List<List<String>> Data = new ArrayList<>();
        
        if (this.reader.hasNextLine()) {
            String unparsedFieldData = this.reader.nextLine();
            
            this.fields = Arrays.asList(unparsedFieldData.split(";"));
        } else {return;}
        
        while (this.reader.hasNextLine()) {
            String unparsedRowData = this.reader.nextLine();
            
            List<String> rowData = Arrays.asList(unparsedRowData.split(";"));
            
            Data.add(rowData);
        }
        
        this.data = Data;
    }

    @Override
    public List<List<String>> getData() {
        return this.data;
    }

    @Override
    public List<String> getRow(String key) {
        for (List<String> rowData : this.data) {
            if (rowData.getFirst().equals(key)) {
                return rowData;
            }
        }
        
        return new ArrayList<>();
    }

    @Override
    public List<String> getColumn(String wantedField) {
        int index = this.getFieldIndex(wantedField);
        List<String> columnData = new ArrayList<>();
        
        if (index == -1) {
            return columnData;
        }
        
        for (List<String> rowData : this.data) {
            String wantedData = rowData.get(index);
            
            columnData.add(wantedData);
        }
        
        return columnData;
    }

    @Override
    public List<String> getFieldName() {
        return this.fields;

    }
    
    private int getFieldIndex(String wantedField) {
        int index = 0;
        for (String field : this.fields) {
            if (field.equals(wantedField)) {
                return index;
            }
            
            index++;
        }
        
        
        return -1;
    }

    @Override
    public List<List<String>> FitlerData(String Field, String Value) {
        List<List<String>> filteredData = new ArrayList<>();
        
        List<String> columnData = this.getColumn(Field);
        
        
        int index = 0;
        for (String currentData : columnData) {
            if (currentData.equals(Value)) {
                filteredData.add(this.data.get(index));
            }
            
            index++;
        }
        
        return filteredData;
    }
}
