package com.example.couponSystem.interfaces;

import com.example.couponSystem.beans.Company;
import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.exceptions.CouponSystemException;

import java.util.List;

/**
 * Interface company service - here we write all the function we need for company service
 */
public interface CompanyServ {

    /**
     * Function to add coupon to the company that does login
     *
     * @param coupon the coupon we want to add
     * @throws CouponSystemException enable to throw exception
     */
    boolean addCoupon(Coupon coupon) throws CouponSystemException;

    /**
     * Function to update coupon
     *
     * @param coupon the coupon we want to update
     * @throws CouponSystemException enable to throw exception
     */
    boolean updateCoupon(Coupon coupon) throws CouponSystemException;

    /**
     * Function to delete coupon from the company
     *
     * @param couponId id of the coupon we want to delete
     * @throws CouponSystemException enable to throw exception
     */
    boolean deleteCoupon(int couponId) throws CouponSystemException;

    /**
     * Function to get all the coupons of the company that does login
     *
     * @return List of all the company coupons
     */
    List<Coupon> getCompanyCoupons();

    /**
     * Function to get company coupons by category
     *
     * @param category the category we want to find coupons
     * @return List of coupons of this category
     */
    List<Coupon> getCompanyCouponsByCategory(Category category);

    /**
     * Function to get company coupons under max price
     *
     * @param maxPrice the max price
     * @return List of the coupons of the company that does login under the max price
     */
    List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice);

    /**
     * Function to get details of the company that does login
     *
     * @return Details of the company
     * @throws CouponSystemException enable to throw exception
     */
    Company getCompanyDetails() throws CouponSystemException;

    Coupon getOneCoupon(int couponId) throws CouponSystemException;
}
