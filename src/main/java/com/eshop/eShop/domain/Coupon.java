package com.eshop.eShop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String couponCode;
    private Double discount;
    private Boolean isPercentage;
    private Boolean isActive;


    @JsonIgnore
    @OneToMany(mappedBy = "coupon")
    private List<Cart> cartList;


}
