package com.example.CustomerProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (customerSSN == null || customerSSN.trim().isEmpty()) {
            throw new IllegalArgumentException(RED + "Invalid SSN number" + RESET);
        } else if (customerSSN.trim().length() != 10) {
            throw new IllegalArgumentException(RED + "Invalid SSN number" + RESET);
        } else {
            this.customerSSN = customerSSN;
        }
        if( productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException(RED + "Product ID cannot be null or empty" + RESET);
        }
        else {
        this.productID = productID;
        }
        this.purchaseDate = purchaseDate;
        this.paid = false;
    }

    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String lineRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter) + "," + this.paid;
    }

    public boolean isPaid() {
       return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getSearchKey() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter);
    }

}
