package com.example.couponSystem.thread;

import com.example.couponSystem.beans.Coupon;
import com.example.couponSystem.repositories.CouponRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Thread class -
 */
@Component
@EnableAsync
@RequiredArgsConstructor
public class CouponDailyJob {

    private final CouponRepo couponRepo;

    /**
     * Function to delete coupons that the end date is expired all day to 1:00 A.M
     */
    @Scheduled(cron = "0 01 00 * * ? ")
    public void scanCoupons() {
        for (Coupon item : couponRepo.findAll()) {
            if (item.getEndDate().isBefore(LocalDate.now())) {
                couponRepo.deleteById(item.getId());
                System.out.println("Coupon " + item.getId() + " Was deleted because it was expired");
            }
        }
    }
}
