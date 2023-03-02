package com.eshop.eShop.dto;

import com.eshop.eShop.domain.Cart;
import com.eshop.eShop.domain.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCartDto {
    private Long id;

    @Min(value = 1,message = "Stock should be more than 1!")
    private Integer quantity;

    @Min(value = 1,message = "Price should be more than 1Rps!")
    private Double price;

    @Min(value = 1,message = "Cost should be more than 1Rps!")
    private Double cost;

    private Product product;

    private Cart cart;
}
