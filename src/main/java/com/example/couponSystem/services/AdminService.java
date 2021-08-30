package com.example.couponSystem.services;

import com.example.couponSystem.beans.Company;
import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.beans.Customer;
import com.example.couponSystem.exceptions.CouponSystemException;
import com.example.couponSystem.interfaces.AdminServ;
import com.example.couponSystem.repositories.CompanyRepo;
import com.example.couponSystem.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Admin service
 */
@Service
@RequiredArgsConstructor
public class AdminService extends ClientService implements AdminServ {

    private final CompanyRepo companyRepo;
    private final CustomerRepo customerRepo;

    /**
     * Function login
     *
     * @param email    email
     * @param password password
     * @return true if the email and the password are true
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            return true;
        } else throw new CouponSystemException("ERROR! WRONG EMAIL, PASSWORD OR CLIENT TYPE! ");
    }

    /**
     * Function to add company to the system
     *
     * @param company the company we want to add to the system
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean addCompany(Company company) throws CouponSystemException {
        if (companyRepo.findByNameOrEmail(company.getName(), company.getEmail()) == null) {
            companyRepo.save(company);
            return true;
        } else throw new CouponSystemException("ERROR! EMAIL OR PASSWORD IS ALREADY EXIST FOR ANOTHER COMPANY");
    }

    /**
     * Function to update company
     *
     * @param company the company we want to update
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean updateCompany(Company company) throws CouponSystemException {
        if (companyRepo.existsById(company.getCompanyId())) {
            companyRepo.save(company);
            return true;
        } else throw new CouponSystemException("ERROR! COMPANY NOT EXIST, CANNOT UPDATE IT!");
    }

    /**
     * Function to delete company from the system
     *
     * @param companyId the id of the company we want to delete
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean deleteCompany(int companyId) throws CouponSystemException {
        if (companyRepo.existsById(companyId)) {
            for (Customer item : getAllCustomers()) {
                for (Coupon index : item.getCoupons()) {
                    if (index.getCompanyId() == companyId) {
                        companyRepo.deleteCouponsFromCustomerCoupons(index.getId());
                    }
                }
            }
            companyRepo.deleteCouponsByCompanyId(companyId);
            companyRepo.deleteById(companyId);
            return true;
        } else throw new CouponSystemException("ERROR! CANNOT DELETE COMPANY THAT NOT EXIST");
    }

    /**
     * Function to get all the companies from the system
     *
     * @return List of all the companies
     */
    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    /**
     * Function to get one company from the system
     *
     * @param companyId the id of the the company we want to get from the system
     * @return Company
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public Company getOneCompany(int companyId) throws CouponSystemException {
        return companyRepo.findById(companyId).orElseThrow(() -> new CouponSystemException("ERROR! COMPANY NOT EXISTS!"));
    }

    /**
     * Function to add customer to the system
     *
     * @param customer the customer we want to add to the system
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean addCustomer(Customer customer) throws CouponSystemException {
        if (customerRepo.findByEmail(customer.getEmail()) == null) {
            customerRepo.save(customer);
            return true;
        } else throw new CouponSystemException("ERROR! EMAIL IS ALREADY USED FOR ANOTHER CUSTOMER!  ");
    }

    /**
     * Function to update customer
     *
     * @param customer the customer we want to update
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean updateCustomer(Customer customer) throws CouponSystemException {
        if (customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return true;
        } else throw new CouponSystemException("ERROR! CUSTOMER NOT EXISTS CANNOT UPDATE HIM! ");
    }

    /**
     * Function to delete customer from the system
     *
     * @param customerId the id of the customer we want to delete from the system
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean deleteCustomer(int customerId) throws CouponSystemException {
        if (customerRepo.existsById(customerId)) {
            customerRepo.deleteById(customerId);
            return true;
        } else throw new CouponSystemException("ERROR! CUSTOMER NOT EXIST, CANNOT DELETE HIM! ");
    }

    /**
     * Function to get all the customers from the system
     *
     * @return List of all the customers
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    /**
     * Function to get one customer from the system
     *
     * @param customerId the id of the customer we want to get
     * @return Customer
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        return customerRepo.findById(customerId).orElseThrow(() -> new CouponSystemException("ERROR! CUSTOMER IS NOT EXISTS!"));
    }
}
