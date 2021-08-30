package com.example.couponSystem.repositories;

import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface - Customer
 */
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    /**
     * Function to find customer by his email
     *
     * @param email the email
     * @return customer with this email
     */
    Customer findByEmail(String email);

    /**
     * Function to find customer by his email and his password
     *
     * @param email    the email
     * @param password the password
     * @return customer with this email and this password
     */
    Customer findByEmailAndPassword(String email, String password);

    /**
     * Function to find customer by coupon
     *
     * @param coupon the coupon
     * @return customer that by this coupon
     */
    Customer findByCoupons(Coupon coupon);

}
