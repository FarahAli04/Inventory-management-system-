package com.example.Admin;

public class EmployeeUser {

    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        if(employeeId == null || employeeId.trim().isEmpty() || employeeId.trim().length() != 5) {
            throw new IllegalArgumentException("Employee ID cannot be null , empty or less than 5 characters long");
        }
         else if (employeeId.charAt(0) != 'E') {
            throw new IllegalArgumentException("Product ID must start with the letter 'E'");
        }
        else if (!employeeId.substring(1).matches("\\d{4}")) {
            throw new IllegalArgumentException("Product ID must be followed by 4 digits");
        }
        else{
            this.employeeId = employeeId;
        }
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        else{
            this.name = name;
        }
        if(email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        else{
            this.email = email;
        }
        if (address == null || address.trim().isEmpty()) {
             throw new IllegalArgumentException("Address cannot be null or empty");
        }
        else{
            this.address = address;
        }
        if(phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        else{
            this.phoneNumber = phoneNumber;
        }
        
    }

    public String lineRepresentation() {
        return this.employeeId + "," + this.name + "," + this.email + "," + this.address + "," + this.phoneNumber;
    }

    public String getSearchKey() {
        return this.employeeId;
    }
    
}
