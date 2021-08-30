package com.example.couponSystem.controllers;

import com.example.couponSystem.beans.Company;
import com.example.couponSystem.beans.Customer;
import com.example.couponSystem.beans.UserDetails;
import com.example.couponSystem.enums.ClientType;
import com.example.couponSystem.exceptions.CouponSystemException;
import com.example.couponSystem.services.AdminService;
import com.example.couponSystem.token.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller Admin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class AdminController {

    private final AdminService adminService;
    private final JWTUtils util;

    @PostMapping("login")
    @CrossOrigin
    public ResponseEntity<?> login(@RequestBody UserDetails myData) {
        try {
            if (adminService.login(myData.getUserEmail(), myData.getUserPassword())) {
                String myToken = util.generateToken(new UserDetails(myData.getUserEmail(), 0, ClientType.ADMINISTRATOR));
                return new ResponseEntity<>(myToken, HttpStatus.ACCEPTED);
            } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (CouponSystemException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    public boolean checkToken(@RequestHeader(name = "Authorization") String token, UserDetails userDetails) {
        return (util.validateToken(token, userDetails));
    }


    /**
     * Function to add company to the system
     *
     * @param company company we want to add
     * @return ResponseEntity for this action
     */
    // ADD COMPANY
    @PostMapping(path = "/addCompany{company}")
    public ResponseEntity<?> addCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR)))
                        .body(adminService.addCompany(company));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to update company
     *
     * @param company company we want to update
     * @return ResponseEntity for this action
     */
    @PutMapping("/updateCompany")
    public ResponseEntity<?> updateCompany(@RequestHeader(name = "Authorization") String token, @RequestBody Company company) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR)))
                        .body(adminService.updateCompany(company));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to delete company from the system
     *
     * @param companyId the id of the company we want to delete
     * @return ResponseEntity for this action
     */
    @DeleteMapping("/deleteCompany{companyId}")
    public ResponseEntity<?> deleteCompany(@RequestHeader(name = "Authorization") String token, @PathVariable int companyId) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR)))
                        .body(adminService.deleteCompany(companyId));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the companies of the system
     *
     * @return ResponseEntity for this action
     */
    @GetMapping("/allCompanies")
    public ResponseEntity<?> getAllCompanies(@RequestHeader(name = "Authorization") String token) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR))) {
            return ResponseEntity.ok()
                    .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR)))
                    .body(adminService.getAllCompanies());
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     * Function to get one company by this is
     *
     * @param companyId the id of the company we want to get
     * @return ResponseEntity for this action
     */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<?> getOneCompany(@RequestHeader(name = "Authorization") String token,@PathVariable int companyId) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR ))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR )))
                        .body(adminService.getOneCompany(companyId));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to add customer to the system
     *
     * @param customer the customer we want to add to the system
     * @return ResponseEntity for this action
     */
    @PostMapping(path = "/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestHeader(name = "Authorization") String token, @RequestBody Customer customer) {
        if (checkToken(token, new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR))) {
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token), 0, ClientType.ADMINISTRATOR)))
                        .body(adminService.addCustomer(customer));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to update customer
     *
     * @param customer the customer we want to update
     * @return ResponseEntity for this action
     */
    @PutMapping("/updateCustomer")
    public ResponseEntity<?> updateCustomer(@RequestHeader(name = "Authorization") String token,@RequestBody Customer customer) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR ))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR)))
                        .body(adminService.updateCustomer(customer));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to delete customer from the system
     *
     * @param customerId the id of the customer we want to delete
     * @return Response entity for this action
     */
    @DeleteMapping("/deleteCustomer{customerId}")
    public ResponseEntity<?> deleteCustomer(@RequestHeader(name = "Authorization") String token,@PathVariable int customerId) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization",util.generateToken(new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR)))
                        .body(adminService.deleteCustomer(customerId));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get all the customers of the system
     *
     * @return ResponseEntity for this action
     */
    @GetMapping("/allCustomers")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR))){
            return ResponseEntity.ok()
                    .header("Authorization",util.generateToken(new UserDetails(util.extractUserName(token), 0,ClientType.ADMINISTRATOR)))
                    .body(adminService.getAllCustomers());
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Function to get one customer from the system
     *
     * @param customerId the id of the customer we want to get
     * @return Response entity for this action
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getOneCustomer(@RequestHeader(name = "Authorization") String token,@PathVariable int customerId) {
        if (checkToken(token,new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR ))){
            try {
                return ResponseEntity.ok()
                        .header("Authorization", util.generateToken(new UserDetails(util.extractUserName(token),0,ClientType.ADMINISTRATOR )))
                        .body(adminService.getOneCustomer(customerId));
            } catch (CouponSystemException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
