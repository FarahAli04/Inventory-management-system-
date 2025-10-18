package com.example.CustomerProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.example.Admin.EmployeeUser;

public abstract class DataBase<T> {

    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    protected String filename;
    protected ArrayList<T> records;

    public DataBase(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException(RED + "Invalid filename: " + filename + RESET);
        } else {
            this.filename = filename;
        }
        this.readFromFile();

    }

    public void readFromFile() {
        records.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    T record = createRecordFrom(line);
                    if (record != null) {
                        records.add(record);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(RED + "Error reading The file: " + e.getMessage() + RESET);
        }
    }
    public abstract T createRecordFrom(String line);
    public ArrayList<T> returnAllRecords() {
        return records;
    }
    public boolean contains(String key) {
        for (T record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public void insertRecord(T record) {
        if (contains(record.getSearchKey())) {
            throw new IllegalArgumentException(RED + "Record with the same key already exists." + RESET);
        } else {
            records.add(record);
        }
    }
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (T record : records) {
                writer.println(record.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println(RED + "Error saving to The file : " + e.getMessage()+ RESET);
        }
    }

}
