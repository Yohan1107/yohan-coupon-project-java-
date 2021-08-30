package com.example.couponSystem.beans;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Bean class: Company
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    @Column(nullable = false)
    private int companyId;
    @Column(nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true,mappedBy = "companyId")
    private List<Coupon> coupons;

}
