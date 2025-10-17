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
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    private ArrayList<CustomerProduct> records;
    private String filename;

    public CustomerProductDatabase(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException(RED + "Invalid filename: " + filename + RESET);
        } else {
            this.filename = filename;
        }
        this.readFromFile();

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
                    System.out.println(RED + "Skipping Invalid record of Length: " + recordStr.length + RESET);
                    continue;
                }
               CustomerProduct cp;
                try {
                    cp = createRecordFrom(line);
                } catch (IllegalArgumentException | DateTimeParseException e) {
                    System.out.println(RED + "Skipping Invalid record: " + e.getMessage() + RESET);
                    continue;
                }
                this.records.add(cp);
            }
            if (emptyFile) {
                System.out.println(RED + "File is Empty or Contains only Blank lines" + RESET);
            }

        } catch (FileNotFoundException e) {
            System.out.println(RED + "Error Locating File" + RESET);

        } catch (IOException e) {
            System.out.println(RED + "Something Went Wrong" +RESET);

        }
    }

    public CustomerProduct createRecordFrom(String line) {
        String[] recordStr = line.split(",");
        if (recordStr.length != 4) {
            throw new IllegalArgumentException(RED + "Invalid record format. Expected 4 fields comma separated but got: " + recordStr.length + RESET);
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

   public void deleteRecord(String key) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    for (int i = 0; i < records.size(); i++) {
        CustomerProduct x = records.get(i);
        String xString = x.getCustomerSSN() + "," + x.getProductID() + "," + x.getPurchaseDate().format(formatter);
        if (xString.equals(key)) {
            records.remove(i);
            System.out.println("Record Removed Successfully");
            return; // Exit after removal
        }
    }
    System.out.println("There is no Record " + key + " in file");
}
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (CustomerProduct record : records) {
                writer.println(record.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println(RED + "Error saving to The file : " + e.getMessage() + RESET);
        }
    }

}
