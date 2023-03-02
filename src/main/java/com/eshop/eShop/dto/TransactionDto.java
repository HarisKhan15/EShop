package com.eshop.eShop.dto;

import com.eshop.eShop.domain.Cart;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {
    private Long id;

    @NotNull
    private Double totalAmount;
    private LocalDate checkOutDate;

    @NotNull
    private Cart cart;
}
