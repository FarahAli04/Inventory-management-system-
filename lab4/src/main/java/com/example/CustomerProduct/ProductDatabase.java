package com.example.CustomerProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ProductDatabase {
    private ArrayList<Product> records;
    private String filename;

    public ProductDatabase(String filename) {
        this.filename = filename;
        records = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
        records.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Product record = createRecordFrom(line);
                    if (record != null) {
                        records.add(record);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading The file: " + e.getMessage());
        }
    }

    public Product createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 6) {
            try {
                String productID = parts[0].trim();
                String productName = parts[1].trim();
                String manufacturerName = parts[2].trim();
                String supplierName = parts[3].trim();
                int quantity = Integer.parseInt(parts[4].trim());
                float price = Float.parseFloat(parts[5].trim());
                return new Product(productID, productName, manufacturerName, supplierName, quantity, price);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing number : " + e.getMessage());
            }
        }
        return null;
    }

    public ArrayList<Product> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (Product record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public Product getRecord(String key) {
        for (Product record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(Product record) {
        if (contains(record.getSearchKey())) {
            throw new IllegalArgumentException("Record with the same key already exists.");
        } else {
            records.add(record);
        }
    }

    public void deleteRecord(String key) {
        records.removeIf(record -> record.getSearchKey().equals(key));
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Product record : records) {
                writer.println(record.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to The file : " + e.getMessage());
        }
    }

}
