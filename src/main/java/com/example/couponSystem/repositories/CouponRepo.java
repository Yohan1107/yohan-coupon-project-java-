package com.example.couponSystem.repositories;

import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository interface - Coupon
 */
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    /**
     * Function to find coupon by title and companyID
     *
     * @param title     the title
     * @param companyId the companyID
     * @return coupon with this title and this companyID
     */
    Coupon findByTitleAndCompanyId(String title, int companyId);

    /**
     * Function to find coupon by category and companyID
     *
     * @param category  the category
     * @param companyId the companyID
     * @return List of coupons with this category and this companyID
     */
    List<Coupon> findByCategoryAndCompanyId(Category category, int companyId);

    /**
     * Function to find coupons under max price an by his companyID
     *
     * @param price     the maxPrice
     * @param companyId the companyID
     * @return List of the coupons of this company that under max price
     */
    List<Coupon> findByPriceLessThanAndCompanyId(double price, int companyId);

    List<Coupon> findByCompanyId(int companyId);


    /**
     * Function to deleteCoupon by couponID
     *
     * @param couponId the couponID
     */
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `customers_coupons` WHERE `coupons_id` = :couponId", nativeQuery = true)
    void deleteCouponByCouponId(int couponId);
}
