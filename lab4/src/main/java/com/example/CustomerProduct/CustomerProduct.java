package com.example.CustomerProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException(RED + "Product ID cannot be null or empty" + RESET);
        } else {
            this.productID = productID;
        }
        if (purchaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(RED + "Purchase date cannot be in the future" + RESET);
        } else {
            this.purchaseDate = purchaseDate;
        }

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
<<<<<<< Updated upstream
=======
    @Override
    public String lineRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter) + "," + this.paid;
    }

>>>>>>> Stashed changesئ
    public boolean isPaid() {
        return this.paid;
    }

    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
<<<<<<< Updated upstream
    
    @Override
    public String lineRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter) + "," + this.paid;
    }

=======
>>>>>>> Stashed changes
    @Override
    public String getSearchKey() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter);
    }

}
