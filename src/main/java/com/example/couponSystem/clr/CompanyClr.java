package com.example.couponSystem.clr;

import com.example.couponSystem.Utils.TablePrinter;
import com.example.couponSystem.Utils.TestsUtils;
import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.enums.Category;
import com.example.couponSystem.enums.ClientType;
import com.example.couponSystem.login.LoginManager;
import com.example.couponSystem.services.ClientService;
import com.example.couponSystem.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//@Component
//@Order(2)
@RequiredArgsConstructor
@EnableScheduling
public class CompanyClr implements CommandLineRunner {

    private final LoginManager loginManager;

    @Override
    public void run(String... args) {
        try {
            ClientService clientService = loginManager.login("company1@gmail.com", "12345", ClientType.COMPANY);
            if (clientService instanceof CompanyService) {
                System.out.println(TestsUtils.COMPANY_TEST);
                System.out.println("COMPANY 1 WAS LOGIN");
                try {
                    System.out.println("ADD COUPON");
                    Coupon coupon1 = Coupon.builder().category(Category.KIDS).title("Where is the pig").description("oh yeah").startDate(LocalDate.now()).endDate(LocalDate.of(2021, 6, 15)).amount(10).price(150.00).image("jpg").build();
                    Coupon coupon2 = Coupon.builder().category(Category.KIDS).title("Where is the cow").description("oh nooo").startDate(LocalDate.now()).endDate(LocalDate.of(2021, 6, 15)).amount(20).price(120.00).image("jpg").build();
                    Coupon coupon3 = Coupon.builder().category(Category.PETS).title("Where is the zeev").description("oh shiiit").startDate(LocalDate.now()).endDate(LocalDate.of(2021, 6, 15)).amount(30).price(300.00).image("jpg").build();
                    ((CompanyService) clientService).addCoupon(coupon1);
                    ((CompanyService) clientService).addCoupon(coupon2);
                    ((CompanyService) clientService).addCoupon(coupon3);
                    System.out.println("Show that we cant add coupon with same title that one of this coupons");
                    ((CompanyService) clientService).addCoupon(coupon1);
                    System.out.println("Show that the coupon was added to the company coupons");
                    TablePrinter.print(((CompanyService) clientService).getCompanyDetails());
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    System.out.println("UPDATE COUPON");
                    Coupon coupon1 = ((CompanyService) clientService).getCouponRepo().getOne(3);
                    coupon1.setAmount(5);
                    ((CompanyService) clientService).updateCoupon(coupon1);
                    System.out.println("Show the coupon was updated");
                    TablePrinter.print(coupon1);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }

                try {
                    System.out.println("GET ALL COMPANY COUPON");
                    TablePrinter.print(((CompanyService) clientService).getCompanyCoupons());
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("GET ALL COMPANY COUPONS BY CATEGORY");
                    TablePrinter.print(((CompanyService) clientService).getCompanyCouponsByCategory(Category.KIDS));
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("GET ALL COMPANY COUPONS BY MAX PRICE");
                    TablePrinter.print(((CompanyService) clientService).getCompanyCouponsByMaxPrice(151));
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("GET COMPANY DETAILS");
                    TablePrinter.print(((CompanyService) clientService).getCompanyDetails());
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }

                try {
                    System.out.println("DELETE COUPON");
                    ((CompanyService) clientService).deleteCoupon(1);
                    System.out.println("show that the coupon was deleted");
                    TablePrinter.print(((CompanyService) clientService).getCompanyDetails().getCoupons());
                } catch (Exception err) {
                    System.err.println(err.getMessage());
                }
            }
            try {
                ClientService clientService1 = loginManager.login("compan@gmail.com", "12345", ClientType.COMPANY);
                if (clientService1 instanceof CompanyService) {
                    System.out.println("hello");
                }
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        } catch (Exception error) {
            System.err.println(error.getMessage());
        }
    }
}
