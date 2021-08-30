package com.example.couponSystem.exceptions;

/**
 * Exception class
 */
public class CouponSystemException extends Exception {
    /**
     * Exception classic
     */
    public CouponSystemException() {
    }

    /**
     * Exception with personal message
     *
     * @param message the message we want to send
     */
    public CouponSystemException(String message) {
        super(message);
    }
}
