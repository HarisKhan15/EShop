package com.eshop.eShop.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CouponDto {

    private Long id;
    @NotEmpty
    private String couponCode;
    @NotNull
    private Double discount;
    @NonNull
    private Boolean isPercentage;

    private Boolean isActive;
}
