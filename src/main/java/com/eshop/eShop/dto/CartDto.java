package com.eshop.eShop.dto;

import com.eshop.eShop.domain.Coupon;
import com.eshop.eShop.domain.Customer;
import com.eshop.eShop.domain.ProductCart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private Long id;

    private LocalDate createDate;

    @NotEmpty
    private String deviceAddress;


    private Coupon coupon;


    private Customer customer;
}
