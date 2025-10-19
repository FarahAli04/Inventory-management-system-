package com.example.Admin;

import com.example.CustomerProduct.DataBase;

public class EmployeeUserDatabase extends DataBase<EmployeeUser> {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
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
}
