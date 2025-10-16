package com.example.CustomerProduct;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class EmployeeRole {
    private CustomerProductDatabase customerProductDatabase;
    private ProductDatabase productsDatabase;

    public EmployeeRole() {
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        this.productsDatabase = new ProductDatabase("Products.txt");
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,int quantity,float price) {
        Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        productsDatabase.insertRecord(newProduct);
        productsDatabase.saveToFile();
    }

    public Product[] getListOfProducts() {
       return productsDatabase.returnAllRecords().toArray(new Product[0]);
       
    }
    public CustomerProduct[] getListOfPurchasingOperations(){
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }
}
