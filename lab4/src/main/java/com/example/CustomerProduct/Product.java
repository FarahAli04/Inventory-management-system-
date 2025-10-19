package com.example.CustomerProduct;

public class Product implements Line {
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        if(productID == null || productID.trim().isEmpty() || productID.trim().length() != 5) {
            throw new IllegalArgumentException("Product ID cannot be null , empty or less than 5 characters long");
        }
        else if ( productID.charAt(0) != 'P') {
            throw new IllegalArgumentException("Product ID must start with the letter 'P'");
        }
        else if (!productID.substring(1).matches("\\d{4}")) {
            throw new IllegalArgumentException("Product ID must be followed by 4 digits");
        }
        else {
            this.productID = productID;
        }
        if( productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        else {  
        this.productName = productName;
        }
        if( manufacturerName == null || manufacturerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer name cannot be null or empty");
        }
        else {
        this.manufacturerName = manufacturerName;
        }
        this.supplierName = supplierName;
        if (this.quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        else {
            this.quantity = quantity;
        }   
        if( price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        else {
            this.price = price;
        }
    }

    
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
<<<<<<< Updated upstream
    public int getQuantity() {
        return quantity;
    }

=======
    public void setPrice(float price) {
        this.price = price;
    }
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
>>>>>>> Stashed changes

    @Override
    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + "," +
                supplierName + "," + quantity + "," + price;
    }
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    @Override
    public String getSearchKey() {
        return productID;
    }

}
