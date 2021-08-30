package com.example.couponSystem.services;

import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.beans.Customer;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.exceptions.CouponSystemException;
import com.example.couponSystem.interfaces.CustomerServ;
import com.example.couponSystem.repositories.CouponRepo;
import com.example.couponSystem.repositories.CustomerRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer service
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class CustomerService extends ClientService implements CustomerServ {

    private int customerId;
    private final CustomerRepo customerRepo;
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
        if (customerRepo.findByEmailAndPassword(email, password) != null) {
            this.setCustomerId(customerRepo.findByEmailAndPassword(email, password).getCustomerId());
            return true;
        } else throw new CouponSystemException("ERROR! WRONG EMAIL, PASSWORD OR CLIENT TYPE! ");
    }

    /**
     * Function to allow the customer that does login to purchase coupon
     *
     * @param coupon the coupon we want to purchase
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public boolean purchaseCoupon(Coupon coupon) throws CouponSystemException {
        if (coupon.getAmount() != 0 || coupon.getEndDate().isAfter(LocalDate.now())) {
            if (customerRepo.findByCoupons(coupon) == null) {
                Customer customer = customerRepo.getOne(customerId);
                List<Coupon> coupons = customer.getCoupons();
                coupons.add(coupon);
                customer.setCoupons(coupons);
                coupon.setAmount(coupon.getAmount() - 1);
                customerRepo.save(customer);
                return true;
            } else throw new CouponSystemException("ERROR! YOU ALREADY HAVE THIS COUPON ! ");
        } else throw new CouponSystemException("COUPON NOT AVAILABLE ! ");
    }

    /**
     * Function to get all the coupons of the customer that does login
     *
     * @return List of th coupons
     */
    @Override
    public List<Coupon> getCustomerCoupons() {
        return customerRepo.getOne(customerId).getCoupons();
    }

    /**
     * Function to get the customer coupons by category
     *
     * @param category the category
     * @return List of coupons of this category that the customer purchased
     */
    @Override
    public List<Coupon> getCustomerCouponsByCategory(Category category) {
        return getCustomerCoupons().stream().filter(item -> item.getCategory() == category).collect(Collectors.toList());
    }

    /**
     * Function to get all the coupons of the customer that does login by maxPrice
     *
     * @param maxPrice the max price
     * @return List of coupons with price under max price
     */
    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) {
        return getCustomerCoupons().stream().filter(item -> item.getPrice() < maxPrice).collect(Collectors.toList());
    }

    /**
     * Function to get details of the customer that does login
     *
     * @return customer details
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public Customer getCustomerDetails() throws CouponSystemException {
        return customerRepo.findById(customerId).orElseThrow(() -> new CouponSystemException("ERROR! CUSTOMER NOT EXISTS! "));
    }

    /**
     * Get one coupon from the system
     *
     * @param couponId the id of the coupon
     * @return Coupon with this id
     * @throws CouponSystemException enable to throw exception
     */
    @Override
    public Coupon getOneCoupon(int couponId) throws CouponSystemException {
        return couponRepo.findById(couponId).orElseThrow(() -> new CouponSystemException("ERROR! COUPON NOT FOUND !"));
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepo.findAll();
    }
}
