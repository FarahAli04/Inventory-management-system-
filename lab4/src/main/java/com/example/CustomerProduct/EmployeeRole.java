/* public double returnProduct(String customerSSN, String productID, 
LocalDate purchaseDate ,LocalDate returnDate): 
Customers can return a product within 14 days of purchase. The method will 
return -1 if: 
• returnDate is earlier than purchaseDate 
• The product is not listed in Products.txt. 
• The string formed to be equal to 
customerSSN+",",productID+","+purchaseDate (in the format DD- 
MM-YYYY) is not listed in CustomersProducts.txt. 
• More than 14 days have passed since the purchase date. 
Otherwise, the method does the following: 
• increments the quantity variable of the product whose product id 
equals the parameter productID by one. 
• removes the line representing the purchasing operation from the file 
CustomersProducts.txt. 
• updates the file Products.txt. 
• returns the product's price.  */
package com.example.CustomerProduct;

import java.time.LocalDate;

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
       if(productsDatabase.contains(productID)){
        Product product = productsDatabase.getRecord(productID);
        if (product.getQuantity() == 0) {
            return false;
        }
        else {
           int newQuantity =  product.getQuantity();
           newQuantity = newQuantity - 1;
           product.setQuantity(newQuantity);
           CustomerProduct newCustomerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
           customerProductDatabase.insertRecord(newCustomerProduct);
           customerProductDatabase.saveToFile();
           productsDatabase.saveToFile();
           return true;
        }
    }
    else return false;
}
public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate ,LocalDate returnDate){
    
}
}
