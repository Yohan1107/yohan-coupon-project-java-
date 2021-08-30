package com.example.couponSystem.services;

import com.example.couponSystem.beans.Company;
import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.exceptions.CouponSystemException;
import com.example.couponSystem.interfaces.CompanyServ;
import com.example.couponSystem.repositories.CompanyRepo;
import com.example.couponSystem.repositories.CouponRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class CompanyService extends ClientService implements CompanyServ {

    private int companyId;
    private final CompanyRepo companyRepo;
    private final CouponRepo couponRepo;

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
        if (companyRepo.findByEmailAndPassword(email, password) != null) {
            this.setCompanyId(companyRepo.findByEmailAndPassword(email, password).getCompanyId());
            return true;
        } else throw new CouponSystemException("ERROR! WRONG EMAIL, PASSWORD OR CLIENT TYPE!");
    }

    /**
     * Function to add coupon to the company that does login
     *
     * @param coupon the coupon we want to add
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean addCoupon(Coupon coupon) throws CouponSystemException {
        if (couponRepo.findByTitleAndCompanyId(coupon.getTitle(), companyId) == null) {
            couponRepo.save(Coupon.builder().companyId(companyId).category(coupon.getCategory()).title(coupon.getTitle()).description(coupon.getDescription()).startDate(coupon.getStartDate()).endDate(coupon.getEndDate()).amount(coupon.getAmount()).price(coupon.getPrice()).image(coupon.getImage()).build());
            return true;
        } else throw new CouponSystemException("ERROR! YOU ALREADY HAVE A COUPON WITH THIS TITLE CHOOSE ANOTHER TITLE !");
    }

    /**
     * Function to update coupon
     *
     * @param coupon the coupon we want to update
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean updateCoupon(Coupon coupon) throws CouponSystemException {
        if (couponRepo.existsById(coupon.getId())) {
            couponRepo.save(coupon);
            return true;
        } else throw new CouponSystemException("ERROR! COUPON IS NOT EXIST CANNOT UPDATE IT !");
    }

    /**
     * Function to delete coupon from the company
     *
     * @param couponId id of the coupon we want to delete
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean deleteCoupon(int couponId) throws CouponSystemException {
        if (couponRepo.existsById(couponId)) {
            couponRepo.deleteCouponByCouponId(couponId);
            couponRepo.deleteById(couponId);
            return true;
        } else throw new CouponSystemException("ERROR! COUPON IS NOT EXIST CANNOT DELETE IT ");
    }

    /**
     * Function to get all the coupons of the company that does login
     *
     * @return List of all the company coupons
     */
    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepo.findByCompanyId(companyId);
    }

    /**
     * Function to get company coupons by category
     *
     * @param category the category we want to find coupons
     * @return List of coupons of this category
     */
    @Override
    public List<Coupon> getCompanyCouponsByCategory(Category category) {
        return couponRepo.findByCategoryAndCompanyId(category, companyId);
    }

    /**
     * Function to get company coupons under max price
     *
     * @param maxPrice the max price
     * @return List of the coupons of the company that does login under the max price
     */
    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) {
        return couponRepo.findByPriceLessThanAndCompanyId(maxPrice, companyId);
    }


    /**
     * Function to get details of the company that does login
     *
     * @return Details of the company
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public Company getCompanyDetails() throws CouponSystemException {
        return companyRepo.findById(companyId).orElseThrow(() -> new CouponSystemException("ERROR! COMPANY NOT EXISTS!"));
    }

    @Override
    public Coupon getOneCoupon(int couponId) throws CouponSystemException {
        return couponRepo.findById(couponId).orElseThrow(()->new CouponSystemException("ERROR! NO COUPON FOUND"));
    }
}
