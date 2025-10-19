package com.example.Admin;
import com.example.CustomerProduct.Line;
public class EmployeeUser implements Line {

    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;


    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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
        else if( (!email.contains("@") && !email.contains(".") && email.indexOf(".") < email.indexOf("@") && email.indexOf("@") == 0)){
            throw new IllegalArgumentException("Email is not valid");
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
        if(phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.trim().length() != 11) {
            throw new IllegalArgumentException("Phone number cannot be null , empty or does not equal 11 characters");
        }
        else{
            this.phoneNumber = phoneNumber;
        }
        
    }

    @Override
    public String lineRepresentation() {
        return this.employeeId + "," + this.name + "," + this.email + "," + this.address + "," + this.phoneNumber;
    }

    @Override
    public String getSearchKey() {
        return this.employeeId;
    }
    
}
