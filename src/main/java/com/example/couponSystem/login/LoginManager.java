package com.example.couponSystem.login;

import com.example.couponSystem.services.ClientService;
import com.example.couponSystem.enums.ClientType;
import com.example.couponSystem.exceptions.CouponSystemException;
import com.example.couponSystem.services.AdminService;
import com.example.couponSystem.services.CompanyService;
import com.example.couponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Login manager - Check the info we get from the user to allow connection.
 */
@Component
@RequiredArgsConstructor
public class LoginManager {

    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    /**
     * Function that check if email and password are true
     *
     * @param email      the email
     * @param password   the password
     * @param clientType the client type
     * @return return the service of the correct user if the email and the password are true
     * @throws CouponSystemException enable to throw exception
     */
    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case ADMINISTRATOR:
                if (adminService.login(email, password)) {
                    return adminService;
                } else throw new CouponSystemException("ERROR! WRONG EMAIL, PASSWORD OR CLIENT TYPE! ");
            case COMPANY:
                if (companyService.login(email, password)) {
                    return companyService;
                } else throw new CouponSystemException("ERROR! WRONG EMAIL, PASSWORD OR CLIENT TYPE! ");
            case CUSTOMER:
                if (customerService.login(email, password)) {
                    return customerService;
                } else throw new CouponSystemException("ERROR! WRONG EMAIL, PASSWORD OR CLIENT TYPE! ");
        }
        return null;
    }

}
