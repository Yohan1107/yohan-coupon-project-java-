package com.example.couponSystem.repositories;

import com.example.couponSystem.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Repository interface - COMPANY
 */
public interface CompanyRepo extends JpaRepository<Company, Integer> {

    /**
     * Function to find company by name and password
     *
     * @param name  the name we want to find
     * @param email the email we want to find
     * @return Company with this name and this email
     */
    Company findByNameOrEmail(String name, String email);

    /**
     * Function to find company by email and password
     *
     * @param email    the email we want to find
     * @param password the password we want to find
     * @return Company with this email and this password
     */
    Company findByEmailAndPassword(String email, String password);

    /**
     * Function to delete coupons of a company by comnpanyId
     *
     * @param companyId the id of the company we want to delete his coupon
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `coupons` WHERE `company_id` = :companyId", nativeQuery = true)
    void deleteCouponsByCompanyId(int companyId);

    /**
     * Function to delete coupons from customer coupons
     *
     * @param couponId the id of the coupon we want to delete from the customer
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `customers_coupons` WHERE `coupons_id` = :couponId", nativeQuery = true)
    void deleteCouponsFromCustomerCoupons(int couponId);


}
