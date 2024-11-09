/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAbstractions.base;

import java.io.BufferedReader;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.FileReader;
import java.util.Iterator;

/**
 *
 * @author JONATHAN
 */
public class FileHandler implements DataWriter, DataContainer {
    private String FileName;
    private FileWriter writer;
    private BufferedReader reader;
    private List<List<String>> data;
    private List<String> fields;
    
    public FileHandler() {
        this.data = new ArrayList<>();
        this.fields = new ArrayList<>();
    }
    
    public FileHandler(String FileName) {
        this.data = new ArrayList<>();
        this.fields = new ArrayList<>();
        this.setFileName(FileName);
    }
    
    private String parseFilePath(String Type) {
        String currentWorkingDirectory = System.getProperty("user.dir");
        
        return currentWorkingDirectory + "\\src\\main\\java\\DataAbstractions\\base\\data\\" + Type + ".txt";
    }
    
    private String parseData(List<String> rowData) { 
        String parsedRowData = "";

        for (String currentData : rowData) {
            parsedRowData += currentData + ";";
        }
        
        return parsedRowData;
    }
    
    private void openFileWriter(String FilePath) {
        try {
            this.writer = new FileWriter(FilePath);
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
    
    private void openFileReader(String FilePath) {
        try {
            FileReader fileReader = new FileReader(FilePath);
            
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
    
    private List<List<String>> sortData(List<List<String>> Data) {
        List<List<String>> sortedData = new ArrayList<>(Data);
        sortedData.sort( (a, b) -> { return a.getFirst().compareTo(b.getFirst()); } ); // Sorts ID Alphabetically
        return sortedData;
    }

    @Override
    public final void setFileName(String FileName) {
        try {
            String FilePath = this.parseFilePath(FileName);
            File file = new File(FilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.FileName = FileName;
        } catch (IOException e) {
            System.out.println("File Not Found!");
            return;
        }
        
        this.getFieldAndData();
    }
    
    @Override
    public String getFileName() {
        return this.FileName;
    }

    @Override
    public void writeData(List<List<String>> Data) {
        Data = this.sortData(Data);
        
        try {
            String FilePath = this.parseFilePath(this.FileName);
            this.openFileWriter(FilePath);
            
            String writeBuffer = this.parseData(this.fields) + "\n";
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
        this.data.add(Data);
        
        this.writeData(this.data);
    }

    @Override
    public void updateData(List<String> Data) {
        int index = 0;
        Iterator<List<String>> iter = this.data.iterator();

        while (iter.hasNext()) {
            List<String> rowData = iter.next();

            if (rowData.getFirst().equals(Data.getFirst())) {
                this.data.remove(index);
                this.data.add(index, Data);
            }
            
            index++;
        }
        
        this.writeData(this.data);
    }

    @Override
    public void deleteData(String ID) {
        int index = 0;
        Iterator<List<String>> iter = this.data.iterator();

        while (iter.hasNext()) {
            List<String> rowData = iter.next();

            if (rowData.getFirst().equals(ID)) {
                this.data.remove(index);
            }
            
            index++;
        }
        
        this.writeData(this.data);
    }

    @Override
    public void updateCompositeData(List<String> Data, List<String> Keys) {
        int index = 0;
        Iterator<List<String>> iter = this.data.iterator();

        while (iter.hasNext()) {
            List<String> rowData = iter.next();
            int numKeysFound = 0;
            for (String Key : Keys) {
                if (rowData.contains(Key)) {
                    numKeysFound++;
                }

                if (numKeysFound == Keys.size()) {
                    this.data.remove(index);
                    this.data.add(index, Data);
                }
            }
            
            index++;
        }
        
            
        this.writeData(this.data);
    }

    @Override
    public void deleteCompositeData(List<String> Keys ) {
        Iterator<List<String>> iter = this.data.iterator();
        
        while (iter.hasNext()) {
            List<String> rowData = iter.next();
            int numKeysFound = 0;
            for (String Key : Keys) {
                if (rowData.contains(Key)) {
                    numKeysFound++;
                }

                if (numKeysFound == Keys.size()) {
                    iter.remove();
                }
            }
        }
        
        this.writeData(this.data);
    }
    
    private void getFieldAndData() {
        List<List<String>> Data = new ArrayList<>();
        
        String FilePath = this.parseFilePath(this.FileName);
        
        this.openFileReader(FilePath);
        
        try {
            String unparsedData;
            if ( ( unparsedData = this.reader.readLine()) != null) {

                this.fields = Arrays.asList(unparsedData.split(";"));
            } else {return;}

            while ( ( unparsedData = this.reader.readLine()) != null ) {
                List<String> rowData = Arrays.asList(unparsedData.split(";"));
                
                List<String> MutableRowData = new ArrayList<>(rowData);
                
                if (MutableRowData.isEmpty()) {
                    continue;
                }

                Data.add(MutableRowData);
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
    public List<String> getRow(String Key) {
        for (List<String> rowData : this.data) {
            if (rowData.getFirst().equals(Key)) {
                return rowData;
            }
        }
        
        return null;
    }
    
    @Override
    public List<String> getCompositeRow(List<String> Keys) {
        
        int iterator = 0;
        for (List<String> rowData : this.data) {
            int numKeysFound = 0;
            for (String Key : Keys) {
                if (rowData.contains(Key)) {
                    numKeysFound++;
                }

                if (numKeysFound == Keys.size()) {
                    return rowData;
                }
            }
            
            iterator++;
        }
        
        return null;
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
        indexes.addAll(Collections.nCopies(this.size(), 1));
        
        
//        If Value in the Corresponding Field and Row is Different, Then Remove
        for (int i = 0; i < Fields.size(); i++) {
            List<String> ColumnData = this.getColumn(Fields.get(i));
            
            String FieldValue = Values.get(i);
            
            for (int j = 0; j < ColumnData.size(); j++) {
                if (indexes.get(j) != 0 && !ColumnData.get(j).equals(FieldValue)) {
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
    
    @Override
    public List<List<String>> FitlerData(String Fields, List<String> Values) {
        List<List<String>> filteredData = new ArrayList<>();
        
        List<String> ColumnData = this.getColumn(Fields);

        for (int i = 0; i < ColumnData.size(); i++) {
            if (Values.contains(ColumnData.get(i))) {
                filteredData.add(this.data.get(i));
            }
        } 
        
        return filteredData;
    }
    
    @Override
    public List<List<String>> FitlerData(String Fields, String Values) {
        List<List<String>> filteredData = new ArrayList<>();
        
        List<String> ColumnData = this.getColumn(Fields);

        for (int i = 0; i < ColumnData.size(); i++) {
            if (Values.equals(ColumnData.get(i))) {
                filteredData.add(this.data.get(i));
            }
        } 
        
        return filteredData;
    }
    
    public int size() {
        return this.data.size();
    }
}
