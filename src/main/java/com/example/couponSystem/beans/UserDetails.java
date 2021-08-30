package com.example.couponSystem.beans;

import com.example.couponSystem.enums.ClientType;
import lombok.Data;

@Data
public class UserDetails {
    private String userEmail;
    private String userPassword;
    private ClientType clientType;
    private int userId;

    public UserDetails(String email, int userId, ClientType userType) {
        this.userEmail = email;
        //this.password = password;
        this.clientType = userType;
        this.userId=userId;
    }
}
