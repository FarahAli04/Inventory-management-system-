package com.example.CustomerProduct;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomerProductDatabase extends DataBase<CustomerProduct> {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public CustomerProductDatabase(String filename) {
        super(filename);

    }
    @Override
    public void readFromFile() {
        String line;
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
                records.add(cp);
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
    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] recordStr = line.split(",");
        if (recordStr.length != 4) {
            throw new IllegalArgumentException(RED + "Invalid record format. Expected 4 fields comma separated but got: " + recordStr.length + RESET);
        }
        String customerSSN = recordStr[0];
        String productID = recordStr[1];
        LocalDate purchaseDate = LocalDate.parse(recordStr[2], DATE_FORMATTER);
        boolean paid = Boolean.parseBoolean(recordStr[3]);
        CustomerProduct cp = new CustomerProduct(customerSSN, productID, purchaseDate);
        cp.setPaid(paid);
        return cp;
    }

    
   @Override
    public boolean contains(String key) {
        for (CustomerProduct x : records) {
            String xString = x.getCustomerSSN() + "," + x.getProductID() + "," + x.getPurchaseDate().format(DATE_FORMATTER);
            if (xString.equals(key)) {
                return true;
            }
        }
        return false;
    }
   @Override
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct x : records) {
            String xString = x.getCustomerSSN() + "," + x.getProductID() + "," + x.getPurchaseDate().format(DATE_FORMATTER);
            if (xString.equals(key)) {
                return x;
            }
        }
        return null;
        // in main ask for contains first, if it is false then there is no record but if there is then call get record
    }

}
