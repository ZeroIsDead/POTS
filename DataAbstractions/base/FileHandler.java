/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions.base;

import java.io.BufferedReader;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileReader;

/**
 *
 * @author JONATHAN
 */
public class FileHandler implements DataWriter, DataContainer {
    private String filePath;
    private FileWriter writer;
    private BufferedReader reader;
    private List<List<String>> data;
    private List<String> fields;
    
    public FileHandler() {
        this.data = new ArrayList<>();
    }
    
    public FileHandler(String filePath) {
        this.data = new ArrayList<>();
        this.setFilePath(filePath);
    }
    
    private String parseData(List<String> rowData) { 
        String parsedRowData = "";

        for (String currentData : rowData) {
            parsedRowData += currentData + ";";
        }
        
        return parsedRowData;
    }
    
    private void openFileWriter() {
        try {
            this.writer = new FileWriter(this.filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private void closeFileWriter() {
        try {
            this.writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private void openFileReader() {
        try {
            FileReader fileReader = new FileReader(this.filePath);
            
            this.reader = new BufferedReader(fileReader);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private void closeFileReader() {
        try {
            this.reader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public final void setFilePath(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.filePath = filePath;
        } catch (IOException e) {
            System.out.println("File Not Found!");
        }
        
        this.getFieldAndData();
    }
    
    @Override
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public void writeData(List<List<String>> Data) {
        try {
            this.openFileWriter();
            
            String writeBuffer = new String();
            for (List<String> rowData : Data) {
               writeBuffer += this.parseData(rowData) + "\n";
            }
            
            this.writer.write(writeBuffer);
            
            this.closeFileWriter();
            
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    @Override
    public void appendData(List<String> Data) {
        try {
            this.openFileWriter();
            
            String parsedRowData = this.parseData(Data) + "\n";
            this.writer.append(parsedRowData); //Wrong
            
            this.closeFileWriter();
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
        
        this.writeData(this.data);
    }

    @Override
    public void deleteCompositeData(List<String> Keys ) {
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
        
        this.writeData(this.data);
    }
    
    private void getFieldAndData() {
        List<List<String>> Data = new ArrayList<>();
        
        this.openFileReader();
        
        try {
            String unparsedData;
            if ( ( unparsedData = this.reader.readLine()) != null) {

                this.fields = Arrays.asList(unparsedData.split(";"));
            } else {return;}

            while ( ( unparsedData = this.reader.readLine()) != null ) {
                List<String> rowData = Arrays.asList(unparsedData.split(";"));

                Data.add(rowData);
            }

            this.data = Data;
        } catch (IOException e) {
            System.out.println(e);
        }
        
        this.closeFileReader();
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
    public List<List<String>> FitlerData(List<String> Fields, List<String> Values) {
        List<List<String>> filteredData = new ArrayList<>();
        
        List<Integer> indexes = new ArrayList<>();
        
//        Creates a List of n 1s;
        indexes.addAll(Collections.nCopies(Fields.size(), 1));
        
        
//        If Value in the Corresponding Field and Row is Different, Then Remove
        for (int i = 0; i < Fields.size(); i++) {
            List<String> ColumnData = this.getColumn(Fields.get(i));
            
            for (int j = 0; j < ColumnData.size(); j++) {
                if (!ColumnData.get(j).equals(Values.get(i))) {
                    indexes.remove(j);
                    indexes.add(j, 0);
                }
            } 
        }
        
        
        for (int i = 0; i < indexes.size(); i++) {
            if (indexes.get(i) == 1) {
                filteredData.add(this.data.get(i));
            }
        }
        
        return filteredData;
    }
}
