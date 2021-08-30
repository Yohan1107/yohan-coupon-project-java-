package com.example.couponSystem.controllers;

import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.beans.UserDetails;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.enums.ClientType;
import com.example.couponSystem.exceptions.CouponSystemException;
import com.example.couponSystem.services.CustomerService;
import com.example.couponSystem.token.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller customer
 */
@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CustomerController {

    private final CustomerService customerService;
    private final JWTUtils util;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userDetails){
        try {
            if (customerService.login(userDetails.getUserEmail(), userDetails.getUserPassword())){
                String myToken = util.generateToken(new UserDetails(userDetails.getUserEmail(), customerService.getCustomerId(), ClientType.CUSTOMER));
                return new ResponseEntity<>(myToken,HttpStatus.ACCEPTED);
            }else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (CouponSystemException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customerToken")
    public boolean checkToken(@RequestHeader(name = "Authorization") String token,UserDetails userDetails){
        return (util.validateToken(token, userDetails));
    }

    /**
     * Function for customer that does login to purchase coupon
     *
     * @param coupon coupon that the customer want to purchase
     * @return ResponseEntity for this action
     */
    @PostMapping("/purchaseCoupon")
    public ResponseEntity<?> purchaseCoupon(@RequestHeader(name = "Authorization") String token,@RequestBody Coupon coupon) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER ))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER)))
                        .body(customerService.purchaseCoupon(coupon));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the coupons from the customer that does login
     *
     * @return ResponseEntity for this action
     */
    @GetMapping("/customerCoupons")
    public ResponseEntity<?> getCustomerCoupons(@RequestHeader(name = "Authorization") String token) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER))){
            return ResponseEntity.ok()
                    .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER )))
                    .body(customerService.getCustomerCoupons());
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the coupons by category of the customer that does login
     *
     * @param category category we want to get the coupons
     * @return ResponseEntity for this action
     */
    @GetMapping("/customerCouponsByCategory/{category}")
    public ResponseEntity<?> getCustomerCouponsByCategory(@RequestHeader(name = "Authorization") String token,@PathVariable Category category) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER ))){
            return ResponseEntity.ok()
                    .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER )))
                    .body(customerService.getCustomerCouponsByCategory(category));
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the coupons by max price of the customer that does login
     *
     * @param maxPrice max price for the coupons we want to get
     * @return ResponseEntity for this action
     */
    @GetMapping("/customerCouponsByPrice/{maxPrice}")
    public ResponseEntity<?> getCustomerCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token,@PathVariable double maxPrice) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER ))){
            return ResponseEntity.ok()
                    .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER)))
                    .body(customerService.getCustomerCouponsByMaxPrice(maxPrice));
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getCoupon{couponId}")
    public ResponseEntity<?> getOneCoupon(@RequestHeader(name = "Authorization") String token,@PathVariable int couponId){
        if (checkToken(token,new UserDetails(util.extractUserName(token), customerService.getCustomerId(),ClientType.CUSTOMER))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER )))
                        .body(customerService.getOneCoupon(couponId));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/allCoupons")
    public ResponseEntity<?> getAllCoupons(@RequestHeader(name = "Authorization") String token){
        if (checkToken(token,new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER ))){
            return ResponseEntity.ok()
                    .header( "Authorization", util.generateToken(new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER )))
                    .body(customerService.getAllCoupons());
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get details of the customer that does login
     *
     * @return ResponseEntity for this action
     */
    @GetMapping("/customerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestHeader(name = "Authorization") String token) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER ))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),customerService.getCustomerId(),ClientType.CUSTOMER)))
                        .body(customerService.getCustomerDetails());
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
