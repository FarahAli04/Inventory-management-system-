package com.example.Admin;
import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database;

    public EmployeeUserDatabase getDatabase() {
        return database;
    }

    public void setDatabase(EmployeeUserDatabase database) {
        this.database = database;
    }
    
    
    public AdminRole(){
        this.database = new EmployeeUserDatabase("Employees.txt");
    }
    
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber){
        EmployeeUser newEmployee =new EmployeeUser(employeeId,name,email,address,phoneNumber);
        database.insertRecord(newEmployee);
        database.saveToFile();
    }
    public EmployeeUser[] getListOfEmployees(){
        ArrayList<EmployeeUser> l=database.returnAllRecords();
        return l.toArray(new EmployeeUser[0]);
    }
    
    public void removeEmployee(String key){
        if (!database.contains(key)) {
            throw new IllegalArgumentException("No employee found with ID: " + key);
        }
        database.deleteRecord(key);
        database.saveToFile();
    }
    public void logout(){
        database.saveToFile();
    }

    
}
