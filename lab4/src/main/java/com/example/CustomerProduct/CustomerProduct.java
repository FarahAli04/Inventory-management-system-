package com.example.CustomerProduct;

import java.time.LocalDate;

public class CustomerProduct implements Line {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;


    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (customerSSN == null || customerSSN.trim().isEmpty() || customerSSN.trim().length() != 10) {
            throw new IllegalArgumentException(RED + "Invalid SSN number" + RESET);
        } else {
            this.customerSSN = customerSSN;
        }
        this.productID = productID;
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
    public boolean isPaid() {
        return this.paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    @Override
    public String lineRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter) + "," + this.paid;
    }

    @Override
    public String getSearchKey() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter);
    }

}
