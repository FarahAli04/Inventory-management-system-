/*public void addProduct(String productID, String productName, String 
manufacturerName, String supplierName, int quantity): adds a new 
product to the file named Products.txt. 
2. public Product[] getListOfProducts(): returns an array that contains all the 
products stored in the file named Products.txt. 
3. public CustomerProduct[] getListOfPurchasingOperations(): returns an 
array that contains all the purchasing operations stored in the file named 
CustomersProducts.txt */
package com.example.CustomerProduct;

public class EmployeeRole{
private CustomerProductDatabase customerProductDatabase;
private ProductDatabase productsDatabase;
public EmployeeRole() {
    this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts");
    this.productsDatabase = new ProductDatabase("Products");
}

}
