package com.example.couponSystem.interfaces;

import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.beans.Customer;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.exceptions.CouponSystemException;

import java.util.List;

/**
 * Interface for the customer service, here we write the functions we will use in the service class
 */
public interface CustomerServ {

    /**
     * Function to allow the customer that does login to purchase coupon
     *
     * @param coupon the coupon we want to purchase
     * @throws CouponSystemException enable to throw exception
     */
    boolean purchaseCoupon(Coupon coupon) throws CouponSystemException;

    /**
     * Function to get all the coupons of the customer that does login
     *
     * @return List of th coupons
     * @throws CouponSystemException enable to throw exception
     */
    List<Coupon> getCustomerCoupons() throws CouponSystemException;

    /**
     * Function to get the customer coupons by category
     *
     * @param category the category
     * @return List of coupons of this category that the customer purchased
     */
    List<Coupon> getCustomerCouponsByCategory(Category category);

    /**
     * Function to get all the coupons of the customer that does login by maxPrice
     *
     * @param maxPrice the max price
     * @return List of coupons with price under max price
     */
    List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice);

    /**
     * Function to get details of the customer that does login
     *
     * @return customer details
     * @throws CouponSystemException enable to throw exception
     */
    Customer getCustomerDetails() throws CouponSystemException;

    /**
     * Get one coupon from the system
     *
     * @param couponId the id of the coupon
     * @return Coupon with this id
     * @throws CouponSystemException enable to throw exception
     */
    Coupon getOneCoupon(int couponId) throws CouponSystemException;

    List<Coupon> getAllCoupons();


}
