package com.example.Admin;

import java.io.*;
import java.util.ArrayList;

public class EmployeeUserDatabase {

    private ArrayList<EmployeeUser> records;
    private String filename;
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
        readFromFile();
    }

    public void readFromFile() {
        records.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    EmployeeUser record = createRecordFrom(line);
                    if (record != null) {
                        records.add(record);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(RED + "Error reading The file: " + e.getMessage() + RESET);
        }
    }

    public EmployeeUser createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            try {
                String employeeId = parts[0].trim();
                String name = parts[1].trim();
                String email = parts[2].trim();
                String address = parts[3].trim();
                String phoneNumber = parts[4].trim();
                return new EmployeeUser(employeeId, name, email, address, phoneNumber);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Error parsing number : " + e.getMessage()+ RESET);
            }
        }
        return null;
    }

    public ArrayList<EmployeeUser> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (EmployeeUser record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public EmployeeUser getRecord(String key) {
        for (EmployeeUser record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(EmployeeUser record) {
        if (contains(record.getSearchKey())) {
            throw new IllegalArgumentException(RED + "Record with the same key already exists." + RESET);
        } else {
            records.add(record);
        }
    }

    public void deleteRecord(String key) {
        records.removeIf(record -> record.getSearchKey().equals(key));
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (EmployeeUser record : records) {
                writer.println(record.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println(RED + "Error saving to The file : " + e.getMessage()+ RESET);
        }
    }
}
