package com.eshop.eShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createDate;
    private String deviceAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "cart")
    private List<ProductCart> productCarts;

    @ManyToOne
    @JoinColumn(name = "couponId", referencedColumnName = "id")
    private Coupon coupon;


    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;
}
