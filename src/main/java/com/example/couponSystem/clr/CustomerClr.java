package com.example.couponSystem.clr;

import com.example.couponSystem.Utils.TablePrinter;
import com.example.couponSystem.Utils.TestsUtils;
import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.enums.ClientType;
import com.example.couponSystem.login.LoginManager;
import com.example.couponSystem.services.ClientService;
import com.example.couponSystem.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

//@Component
//@Order(3)
@RequiredArgsConstructor
@EnableScheduling
public class CustomerClr implements CommandLineRunner {

    private final LoginManager loginManager;

    @Override
    public void run(String... args) {
        try {
            System.out.println(TestsUtils.CUSTOMER_TEST);
            ClientService clientService = loginManager.login("yohanOlivier@gmail.com", "234567", ClientType.CUSTOMER);
            if (clientService instanceof CustomerService) {
                System.out.println("Customer 1 was login");
                Coupon coupon = ((CustomerService) clientService).getOneCoupon(3);
                try {
                    System.out.println("PURCHASE COUPON");
                    ((CustomerService) clientService).purchaseCoupon(coupon);
                    System.out.println("Show that customer cannot purchase 2 times the same coupon");
                    ((CustomerService) clientService).purchaseCoupon(coupon);
                    System.out.println("Show that the amount is down");
                    TablePrinter.print(coupon);
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("GET ALL CUSTOMER COUPONS");
                    TablePrinter.print(((CustomerService) clientService).getCustomerCoupons());
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("GET ALL CUSTOMER COUPONS BY CATEGORY");
                    TablePrinter.print(((CustomerService) clientService).getCustomerCouponsByCategory(Category.KIDS));
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("GET ALL CUSTOMER COUPONS BY MAX PRICE");
                    TablePrinter.print(((CustomerService) clientService).getCustomerCouponsByMaxPrice(180.00));
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("GET CUSTOMER DETAILS");
                    TablePrinter.print(((CustomerService) clientService).getCustomerDetails());
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }
            }
            try {
                ClientService clientService1 = loginManager.login("pp@gmail.com", "123456", ClientType.CUSTOMER);
                if (clientService1 instanceof CustomerService) {
                    System.out.println("hello");
                }
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }
}
