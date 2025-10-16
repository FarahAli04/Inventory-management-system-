package com.example.CustomerProduct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerProductDatabase {

    private ArrayList<CustomerProduct> records;
    private String filename;

    public CustomerProductDatabase(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid filename: " + filename);
        } else {
            this.filename = filename;
        }

    }

    public void readFromFile() {
        String line;
        this.records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null) {
                String[] recordStr = line.split(",");
                String customerSSN = recordStr[0];
                String productID = recordStr[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate purchaseDate = LocalDate.parse(recordStr[2], formatter);
                boolean paid = Boolean.parseBoolean(recordStr[3]);
                CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate, paid);
                this.records.add(cp);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error Locating File");

        } catch (IOException e) {
            System.out.println("Something Went Wrong");

        }
    }

    public CustomerProduct createRecordFrom(String line) {
        String[] recordStr = line.split(",");
        if (recordStr.length != 4)
        {
            throw new IllegalArgumentException("Invalid record format. Expected 4 fields comma separated but got: " + recordStr.length);
        }
        String customerSSN = recordStr[0];
        String productID = recordStr[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate purchaseDate = LocalDate.parse(recordStr[2], formatter);
        boolean paid = Boolean.parseBoolean(recordStr[3]);
        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate, paid);
        return cp;
    }

    public ArrayList<CustomerProduct> returnAllRecords()
    {
        
    }

}
