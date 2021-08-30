package com.example.couponSystem.services;

import com.example.couponSystem.exceptions.CouponSystemException;

public abstract class ClientService {

    public abstract boolean login(String email, String password) throws CouponSystemException;
}
