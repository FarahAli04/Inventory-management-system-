package com.example.CustomerProduct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CustomerProductDatabase{

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
        boolean emptyFile = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                emptyFile = false;
                String[] recordStr = line.split(",");
                if (recordStr.length != 4) {
                    System.out.println("Skipping Invalid record of Length: " + recordStr.length);
                    continue;
                }
                try {
                    String customerSSN = recordStr[0].trim();
                    String productID = recordStr[1].trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate purchaseDate = LocalDate.parse(recordStr[2].trim(), formatter);
                    boolean paid = Boolean.parseBoolean(recordStr[3].trim());
                    CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
                    cp.setPaid(paid);
                    this.records.add(cp);

                } catch (DateTimeParseException e) {
                    System.out.println("Skipping record with invalid date format: " + line);
                } catch (Exception e) {
                    System.out.println("Skipping invalid record: " + line);
                }
            }
            if (emptyFile) {
                System.out.println("File is Empty or Contains only Blank lines");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error Locating File");

        } catch (IOException e) {
            System.out.println("Something Went Wrong");

        }
    }

    public CustomerProduct createRecordFrom(String line) {
        String[] recordStr = line.split(",");
        if (recordStr.length != 4) {
            throw new IllegalArgumentException("Invalid record format. Expected 4 fields comma separated but got: " + recordStr.length);
        }
        String customerSSN = recordStr[0];
        String productID = recordStr[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate purchaseDate = LocalDate.parse(recordStr[2], formatter);
        boolean paid = Boolean.parseBoolean(recordStr[3]);
        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
        cp.setPaid(paid);
        return cp;
    }

    public ArrayList<CustomerProduct> returnAllRecords() {
        return this.records;
    }

    public boolean contains(String key) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (CustomerProduct x : records) {
            String xString = x.getCustomerSSN() + "," + x.getProductID() + "," + x.getPurchaseDate().format(formatter);
            if (xString.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public CustomerProduct getRecord(String key) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (CustomerProduct x : records) {
            String xString = x.getCustomerSSN() + "," + x.getProductID() + "," + x.getPurchaseDate().format(formatter);
            if (xString.equals(key)) {
                return x;
            }
        }
        return null;
        // in main ask for contains first, if it is false then there is no record but if there is then call get record
    }


    public void insertRecord(CustomerProduct record)
    {
        this.records.add(record);
    }

    public void deleteRecord(String key)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (CustomerProduct x : records) {
            String xString = x.getCustomerSSN() + "," + x.getProductID() + "," + x.getPurchaseDate().format(formatter);
            if (xString.equals(key)) {
                this.records.remove(x);
            }
        }
        // in main ask for contains first, if it is false then there is no record but if there is then call get delete
    }
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("CustomersProducts.txt"))) {
            for (CustomerProduct record : records) {
                writer.println(record.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to The file : " + e.getMessage());
        }
    }

}
