package com.example.couponSystem.interfaces;

import com.example.couponSystem.beans.Company;
import com.example.couponSystem.beans.Customer;
import com.example.couponSystem.exceptions.CouponSystemException;

import java.util.List;

/**
 * Interface for the admin service, here we write the functions we will use in the service class
 */
public interface AdminServ {

    /**
     * Function to add company to the system
     *
     * @param company the company we want to add to the system
     * @throws CouponSystemException enable to throw exception
     */
    boolean addCompany(Company company) throws CouponSystemException;

    /**
     * Function to update company
     *
     * @param company the company we want to update
     * @throws CouponSystemException enable to throw exception
     */
    boolean updateCompany(Company company) throws CouponSystemException;

    /**
     * Function to delete company from the system
     *
     * @param companyId the id of the company we want to delete
     * @throws CouponSystemException enable to throw exception
     */
    boolean deleteCompany(int companyId) throws CouponSystemException;

    /**
     * Function to get all the companies from the system
     *
     * @return List of all the companies
     */
    List<Company> getAllCompanies();

    /**
     * Function to get one company from the system
     *
     * @param companyId the id of the the company we want to get from the system
     * @return Company
     * @throws CouponSystemException enable to throw exception
     */
    Company getOneCompany(int companyId) throws CouponSystemException;

    /**
     * Function to add customer to the system
     *
     * @param customer the customer we want to add to the system
     * @throws CouponSystemException enable to throw exception
     */
    boolean addCustomer(Customer customer) throws CouponSystemException;

    /**
     * Function to update customer
     *
     * @param customer the customer we want to update
     * @throws CouponSystemException enable to throw exception
     */
    boolean updateCustomer(Customer customer) throws CouponSystemException;

    /**
     * Function to delete customer from the system
     *
     * @param customerId the id of the customer we want to delete from the system
     * @throws CouponSystemException enable to throw exception
     */
    boolean deleteCustomer(int customerId) throws CouponSystemException;

    /**
     * Function to get all the customers from the system
     *
     * @return List of all the customers
     */
    List<Customer> getAllCustomers();

    /**
     * Function to get one customer from the system
     *
     * @param customerId the id of the customer we want to get
     * @return Customer
     * @throws CouponSystemException enable to throw exception
     */
    Customer getOneCustomer(int customerId) throws CouponSystemException;

}
