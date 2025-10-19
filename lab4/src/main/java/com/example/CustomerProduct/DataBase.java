package com.example.CustomerProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class DataBase<T extends Line> {

    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";
    protected String filename;
    protected ArrayList<T> records;

    public String getFilename() {
        return filename;
    }

    public DataBase(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException(RED + "Invalid filename: " + filename + RESET);
        } else {
            this.filename = filename;
        }
        this.records = new ArrayList<>();
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
            System.out.println(RED + "Error saving to The file : " + e.getMessage() + RESET);
        }
    }

    public T getRecord(String key) {
        for (T record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void deleteRecord(String key) {
        records.removeIf(record -> record.getSearchKey().equals(key));
    }

}
