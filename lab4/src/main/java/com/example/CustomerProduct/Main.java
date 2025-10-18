package com.example.CustomerProduct;

import java.time.LocalDate;
import java.util.Scanner;

import com.example.Admin.AdminRole;
import com.example.Admin.EmployeeUser;

public class Main {
    public static final String RED = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        System.out.println(YELLOW + "~~~Inventory management system~~~" + RESET);
        System.out.println("1) Admin             2) Employee");
        System.out.print("Choose between the above choices: ");
        int x = scanner.nextInt();
        switch (x) {
            case 1:
                while (flag) {
                    System.out.println(PURPLE + "Avaiable operations: " + RESET);
                    System.out.println("1) Add new employees to the system");
                    System.out.println("2) View all employees ");
                    System.out.println("3) Remove an employee from the system");
                    System.out.println("4) Log out");
                    System.out.print("your choice: ");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            scanner.nextLine();
                            System.out.println("Enter employee ID (E + 4 digits): ");
                            String employeeId = scanner.nextLine().trim();
                            System.out.println("Enter employee name: ");
                            String name = scanner.nextLine().trim();
                            System.out.println("Enter employee email: ");
                            String email = scanner.nextLine().trim();
                            System.out.println("Enter employee address: ");
                            String address = scanner.nextLine().trim();
                            System.out.println("Enter employee phone number: ");
                            String phoneNumber = scanner.nextLine().trim();
                            AdminRole adminRoleAdd = new AdminRole();
                            try {
                                adminRoleAdd.addEmployee(employeeId, name, email, address, phoneNumber);
                                System.out.println(GREEN + "Employee added successfully!" + RESET);
                            } catch (IllegalArgumentException e) {
                                System.out.println(RED + "Error: " + e.getMessage() + RESET);
                                break;
                            }
                            break;

                        case 2:
                            System.out.println(BLUE + "List of Employees: " + RESET);
                            AdminRole adminRoleView = new AdminRole();
                            EmployeeUser[] employees = adminRoleView.getListOfEmployees();
                            if (employees.length == 0) {
                                System.out.println(RED + "No employees found in the system." + RESET);
                                break;
                            } else {
                                for (EmployeeUser emp : employees) {
                                    System.out.println(emp.lineRepresentation());
                                }
                            }
                            break;

                        case 3:
                            System.out.println("Enter employee ID to remove (E + 4 digits): ");
                            scanner.nextLine();
                            String empIdToRemove = scanner.nextLine().trim();
                            AdminRole adminRoleRemove = new AdminRole();
                            try {
                            adminRoleRemove.removeEmployee(empIdToRemove);
                            System.out.println(GREEN + "Employee removed successfully!" + RESET); 
                            } catch (IllegalArgumentException e) {
                            System.out.println(RED + "Error: " + e.getMessage() + RESET);
                            break;
                            }
                            
                            break;
                        case 4:
                            AdminRole adminRole = new AdminRole();
                            adminRole.logout();
                            System.out.println(GREEN + "Logged out successfully" + RESET);
                            flag = false;
                            break;

                        default:
                            System.out.println(RED + "Error!!! Invalid Input " + RESET);
                            break;
                    }

                }

                break;
            case 2:
                while (flag) {
                    System.out.println(PURPLE + "Avaiable operations: " + RESET);
                    System.out.println("1) Add new products to the inventory");
                    System.out.println("2) View all products ");
                    System.out.println("3) View all purchaes ");
                    System.out.println("4) Sell products to a customer");
                    System.out.println("5) return a product");
                    System.out.println("6) Apply Payment");
                    System.out.println("7) Log out");
                    System.out.print("your choice: ");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            scanner.nextLine();
                            System.out.print("Enter product ID (P + 4digits): ");
                            String productID = scanner.nextLine().trim();
                            System.out.println();
                            System.out.println("Enter product name: ");
                            String productName = scanner.nextLine().trim();
                            System.out.println();
                            System.out.println("Enter manufacturer name: ");
                            String manufacturerName = scanner.nextLine().trim();
                            System.out.println();
                            System.out.println("Enter supplier name: ");
                            String supplierName = scanner.nextLine().trim();
                            System.out.println();
                            System.out.println("Enter quantity: ");
                            int quantity = scanner.nextInt();
                            System.out.println();
                            System.out.println("Enter price: ");
                            float price = scanner.nextFloat();
                            System.out.println();
                            EmployeeRole employeeRole = new EmployeeRole();
                            try {
                                employeeRole.addProduct(productID, productName, manufacturerName, supplierName,
                                        quantity, price);
                                System.out.println(GREEN + "Product added successfully!" + RESET);
                            } catch (IllegalArgumentException e) {
                                System.out.println(RED + "Error: " + e.getMessage() + RESET);
                                break;
                            }
                            
                            break;
                        case 2:
                            scanner.nextLine();
                            EmployeeRole empRole = new EmployeeRole();
                            Product[] products = empRole.getListOfProducts();
                            if (products.length == 0) {
                                System.out.println(RED + "No products available in the inventory." + RESET);
                                break;
                            } else {
                                System.out.println(BLUE + "List of Products: " + RESET);
                                for (Product prod : products) {
                                    System.out.println(prod.lineRepresentation());
                                }
                                break;
                            }
                        case 3:
                            scanner.nextLine();
                            EmployeeRole empRole1 = new EmployeeRole();
                            CustomerProduct[] customerProducts = empRole1.getListOfPurchasingOperations();
                            if (customerProducts.length == 0) {
                                break;
                            } else {
                                System.out.println(BLUE + "List of Purchases: " + RESET);
                                for (CustomerProduct custProd : customerProducts) {
                                    System.out.println(custProd.lineRepresentation());
                                }
                                break;
                            }
                        case 4:
                            scanner.nextLine();
                            System.out.print("Enter customer SSN: ");
                            String customerSSN = scanner.nextLine().trim();
                            System.out.println();
                            System.out.print("Enter product ID (P + 4 digits): ");
                            String prodID = scanner.nextLine().trim();
                            System.out.println();
                            System.out.print("Enter purchase date (YYYY-MM-DD): ");
                            String date = scanner.nextLine();
                            LocalDate purchaseDate = LocalDate.parse(date);
                            System.out.println();
                            EmployeeRole empRole2 = new EmployeeRole();
                            try {
                                boolean success = empRole2.purchaseProduct(customerSSN, prodID, purchaseDate);
                                if (success) {
                                    System.out.println(GREEN + "Product purchased successfully!" + RESET);
                                } else {
                                    System.out.println(RED
                                            + "Purchase failed! Product may be out of stock or does not exist."
                                            + RESET);
                                }
                            } catch (Exception e) {
                                System.out.println(RED + "Error: " + e.getMessage() + RESET);
                                break;
                            }
                            break;
                        case 5:
                            scanner.nextLine();
                            System.out.print("Enter customer SSN: ");
                            String custSSN = scanner.nextLine().trim();
                            System.out.println();
                            System.out.print("Enter product ID (P + 4 digits): ");
                            String productIDReturn = scanner.nextLine().trim();
                            System.out.println();
                            System.out.print("Enter purchase date (YYYY-MM-DD): ");
                            String purchaseDateStr = scanner.nextLine();
                            LocalDate purchaseDateReturn = LocalDate.parse(purchaseDateStr);
                            /*
                             * System.out.print("Enter return date (YYYY-MM-DD): ");
                             * String returnDateStr = scanner.nextLine();
                             * LocalDate returnDate = LocalDate.parse(returnDateStr);
                             */
                            LocalDate returnDate = LocalDate.now();
                            EmployeeRole empRole3 = new EmployeeRole();
                            try {
                                double refundAmount = empRole3.returnProduct(custSSN, productIDReturn,
                                        purchaseDateReturn,
                                        returnDate);
                                if (refundAmount == -1) {
                                    System.out.println(RED
                                            + "Return failed! Invalid return date , product does not exist or product is not paid for."
                                            + RESET);
                                } else {
                                    System.out.println(GREEN + "Product returned successfully! Refund Amount: "
                                            + refundAmount + RESET);
                                }
                            } catch (Exception e) {
                                System.out.println(RED + "Error: " + e.getMessage() + RESET);
                                break;
                            }

                            break;
                        case 6:
                            scanner.nextLine();
                            System.out.print("Enter customer SSN: ");
                            String custSSNPay = scanner.nextLine().trim();
                            System.out.println();
                            System.out.print("Enter purchase date (YYYY-MM-DD): ");
                            String purchaseDateStrPay = scanner.nextLine();
                            LocalDate purchaseDatePay = LocalDate.parse(purchaseDateStrPay);
                            EmployeeRole empRole4 = new EmployeeRole();
                            boolean paymentSuccess = empRole4.applyPayment(custSSNPay, purchaseDatePay);
                            if (paymentSuccess) {
                                System.out.println(GREEN + "Payment applied successfully!" + RESET);
                            } else {
                                System.out.println(RED + "Payment failed! Record not found." + RESET);
                            }
                            break;
                        case 7:
                            EmployeeRole empRole5 = new EmployeeRole();
                            empRole5.logout();
                            System.out.println(GREEN + "Logged out successfully" + RESET);
                            flag = false;
                            break;

                        default:
                            System.out.println(RED + "Error!!! Invalid Input " + RESET);
                            break;
                    }

                }
                break;

            default:
                System.out.println(RED + "Error!!! Invalid Input " + RESET);
                break;
        }
    }
}
