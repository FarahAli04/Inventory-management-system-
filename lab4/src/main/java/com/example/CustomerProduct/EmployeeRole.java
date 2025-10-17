package com.example.CustomerProduct;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmployeeRole {
    private CustomerProductDatabase customerProductDatabase;
    private ProductDatabase productsDatabase;

    public EmployeeRole() {
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        this.productsDatabase = new ProductDatabase("Products.txt");
        customerProductDatabase.readFromFile();
        productsDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,
            int quantity, float price) {
        Product newProduct = new Product(productID, productName, manufacturerName, supplierName, quantity, price);
        productsDatabase.insertRecord(newProduct);
        productsDatabase.saveToFile();
    }

    public Product[] getListOfProducts() {
        return productsDatabase.returnAllRecords().toArray(new Product[0]);

    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (productsDatabase.contains(productID)) {
            Product product = productsDatabase.getRecord(productID);
            if (product.getQuantity() == 0) {
                return false;
            } else {
                int newQuantity = product.getQuantity();
                newQuantity = newQuantity - 1;
                product.setQuantity(newQuantity);
                CustomerProduct newCustomerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
                customerProductDatabase.insertRecord(newCustomerProduct);
                customerProductDatabase.saveToFile();
                productsDatabase.saveToFile();
                return true;
            }
        } else
            return false;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }

        if (!productsDatabase.contains(productID)) {
            return -1;
        }

        CustomerProduct customerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
        String searchKey = customerProduct.getSearchKey();

        if (!customerProductDatabase.contains(searchKey)) {
            return -1;
        }

        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);

        if (daysBetween > 14) {
            return -1;
        }

        Product product = productsDatabase.getRecord(productID);
        int newQuantity = product.getQuantity();
        newQuantity = newQuantity + 1;
        product.setQuantity(newQuantity);
        customerProductDatabase.deleteRecord(searchKey);
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        return product.getPrice();

    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        for (CustomerProduct record : customerProductDatabase.returnAllRecords()) {
            if (record.getCustomerSSN().equals(customerSSN) && record.getPurchaseDate().equals(purchaseDate)) {
                if (!record.isPaid()) {
                    record.setPaid(true);
                    customerProductDatabase.saveToFile();
                    return true;
                }
            }
        }
        return false;
    }

    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }

}
