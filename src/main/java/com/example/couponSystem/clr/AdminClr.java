package com.example.couponSystem.clr;

import com.example.couponSystem.Utils.TablePrinter;
import com.example.couponSystem.Utils.TestsUtils;
import com.example.couponSystem.beans.Company;
import com.example.couponSystem.beans.Customer;
import com.example.couponSystem.enums.ClientType;
import com.example.couponSystem.login.LoginManager;
import com.example.couponSystem.services.AdminService;
import com.example.couponSystem.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


//@Component
//@Order(1)
@RequiredArgsConstructor
@EnableScheduling
public class AdminClr implements CommandLineRunner {
    private final LoginManager loginManager;

    @Override
    public void run(String... args) {
        try {
            ClientService clientService = loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
            if (clientService instanceof AdminService) {
                System.out.println(TestsUtils.ADMIN_TEST);
                /*try {
                    // ADD COMPANY
                    System.out.println("ADD COMPANY");
                    Company company1 = Company.builder().name("Company1").email("company1@gmail.com").password("12345").build();
                    Company company2 = Company.builder().name("Company2").email("company2@gmail.com").password("23456").build();
                    Company company3 = Company.builder().name("Company3").email("company3@gmail.com").password("34567").build();
                    ((AdminService) clientService).addCompany(company1);
                    ((AdminService) clientService).addCompany(company2);
                    ((AdminService) clientService).addCompany(company3);
                    //Show that the company was added
                    TablePrinter.print(((AdminService) clientService).getAllCompanies());
                    //Show that we cant add 2 times the same company
                    ((AdminService) clientService).addCompany(company1);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    System.out.println("UPDATE COMPANY");
                    Company company1 = ((AdminService) clientService).getOneCompany(3);
                    company1.setEmail("theBestGroup@gmail.com");
                    ((AdminService) clientService).updateCompany(company1);
                    //Show that the company was updated
                    TablePrinter.print(((AdminService) clientService).getOneCompany(3));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    //DELETE COMPANY
                    System.out.println("DELETE COMPANY");
                    ((AdminService) clientService).deleteCompany(2);
                    //show the company was deleted
                    TablePrinter.print(((AdminService) clientService).getAllCompanies());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                // GET ALL COMPANIES
                System.out.println("GET ALL COMPANIES");
                TablePrinter.print(((AdminService) clientService).getAllCompanies());

                try {
                    // GET ONE COMPANY
                    System.out.println("GET ONE COMPANY");
                    TablePrinter.print(((AdminService) clientService).getOneCompany(1));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    //ADD CUSTOMER
                    System.out.println("ADD CUSTOMER");
                    Customer customer1 = Customer.builder().firstName("Omer").lastName("Amsallem").email("o.a@gmail.com").password("123456").build();
                    Customer customer2 = Customer.builder().firstName("Yohan").lastName("Olivier").email("y.o@gmail.com").password("234567").build();
                    Customer customer3 = Customer.builder().firstName("Eliran").lastName("Takiya").email("o.a@gmail.com").password("345678").build();
                    ((AdminService) clientService).addCustomer(customer1);
                    ((AdminService) clientService).addCustomer(customer2);
                    ((AdminService) clientService).addCustomer(customer3);
                    //Show the customer was added
                    TablePrinter.print(((AdminService) clientService).getAllCustomers());
                    //Show we cant add 2 times the same customer
                    ((AdminService) clientService).addCustomer(customer1);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    //UPDATE CUSTOMER
                    System.out.println("UPDATE CUSTOMER");
                    Customer customer1 = ((AdminService) clientService).getOneCustomer(2);
                    customer1.setEmail("yohanOlivier@gmail.com");
                    ((AdminService) clientService).updateCustomer(customer1);
                    //Show that customer was updated
                    TablePrinter.print(((AdminService) clientService).getOneCustomer(2));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    //DELETE CUSTOMER
                    System.out.println("DELETE CUSTOMER");
                    ((AdminService) clientService).deleteCustomer(3);
                    //Show that customer was deleted
                    TablePrinter.print(((AdminService) clientService).getAllCustomers());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                //GET ALL CUSTOMERS
                System.out.println("GET ALL CUSTOMERS");
                TablePrinter.print(((AdminService) clientService).getAllCustomers());

                try {
                    //GET ONE CUSTOMER
                    System.out.println("GET ONE CUSTOMER");
                    TablePrinter.print(((AdminService) clientService).getOneCustomer(1));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                try {
                    System.out.println("BAD LOGIN");
                    ClientService clientService1 = loginManager.login("admn@admin.com", "admin", ClientType.ADMINISTRATOR);
                    if (clientService1 instanceof AdminService) {
                        System.out.println("hello");
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                 */
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
}

