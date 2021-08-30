package com.example.couponSystem.controllers;

import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.beans.UserDetails;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.enums.ClientType;
import com.example.couponSystem.exceptions.CouponSystemException;
import com.example.couponSystem.services.CompanyService;
import com.example.couponSystem.token.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller Company
 */
@RestController
@RequestMapping("company")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class CompanyController {

    private final CompanyService companyService;
    private final JWTUtils util;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserDetails userData) {
        try {
            if (companyService.login(userData.getUserEmail(), userData.getUserPassword())) {
                String myToken = util.generateToken(new UserDetails(userData.getUserEmail(), companyService.getCompanyId(), ClientType.COMPANY));
                return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (CouponSystemException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("companyToken")
    public boolean checkToken(@RequestHeader(name = "Authorization") String token, UserDetails userDetails) {
        return (util.validateToken(token, userDetails));
    }

    /**
     * Function to add coupon to the system
     *
     * @param coupon the coupon we want to add
     * @return ResponseEntity for this action
     */
    @PostMapping("/addCoupon")
    public ResponseEntity<?> addCoupon(@RequestHeader(name = "Authorization") String token, @RequestBody Coupon coupon) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY)))
                        .body(companyService.addCoupon(coupon));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to update coupon
     *
     * @param coupon the coupon we want to update
     * @return ResponseEntity for this action
     */
    @PutMapping("/updateCoupon{coupon}")
    public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader(name = "Authorization") String token) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY)))
                        .body(companyService.updateCoupon(coupon));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to delete coupon from the system
     *
     * @param couponId the id of the coupon we want to delete
     * @return ResponseEntity for this action
     */
    @DeleteMapping("/deleteCoupon{couponId}")
    public ResponseEntity<?> deleteCoupon(@RequestHeader(name = "Authorization") String token, @PathVariable int couponId) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY)))
                        .body(companyService.deleteCoupon(couponId));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the coupon of the company that does login
     *
     * @return ResponseEntity for this action
     */
    @GetMapping("/companyCoupons")
    public ResponseEntity<?> getCompanyCoupons(@RequestHeader(name = "Authorization") String token) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY))) {
            return ResponseEntity.ok()
                    .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY)))
                    .body(companyService.getCompanyCoupons());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the coupons by category of the company that does login
     *
     * @param category category of the coupons we want to get
     * @return ResponseEntity for this action
     */
    @GetMapping("/couponsByCategory/{category}")
    public ResponseEntity<?> getCompanyCouponsByCategory(@RequestHeader(name = "Authorization") String token, @PathVariable Category category) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY))) {
            return ResponseEntity.ok()
                    .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY)))
                    .body(companyService.getCompanyCouponsByCategory(category));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the coupons of the company that does login under max price
     *
     * @param maxPrice the max price
     * @return ResponseEntity for this action
     */
    @GetMapping("/couponsByMaxPrice{maxPrice}")
    public ResponseEntity<?> getCompanyCouponsByMaxPrice(@RequestHeader(name = "Authorization") String token, @PathVariable double maxPrice) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY))) {
            return ResponseEntity.ok()
                    .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY)))
                    .body(companyService.getCompanyCouponsByMaxPrice(maxPrice));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getOneCoupon/{couponId}")
    public ResponseEntity<?> getOneCoupon(@RequestHeader(name = "Authorization")String token,@PathVariable int couponId) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),companyService.getCompanyId(),ClientType.COMPANY ))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),companyService.getCompanyId(),ClientType.COMPANY)))
                        .body(companyService.getOneCoupon(couponId));

            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get details of the company that does login
     *
     * @return ResponseEntity for this action
     */
    @GetMapping("/CompanyDetails")
    public ResponseEntity<?> getCompanyDetails(@RequestHeader(name = "Authorization") String token) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), companyService.getCompanyId(), ClientType.COMPANY)))
                        .body(companyService.getCompanyDetails());
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
