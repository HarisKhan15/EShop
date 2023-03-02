package com.eshop.eShop.dto;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.domain.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    @NotBlank
    private String name;
    @NotNull
    @Min(value = 1,message = "Price should be more than 1Rps!")
    private Double price;
    @NotNull
    @Min(value = 1,message = "Cost should be more than 1Rps!")
    private Double cost;
    @NotNull
    @Min(value = 1,message = "Stock should be more than 1!")
    private Integer stock;
    private String image;
    private Boolean isActive;

    @NotNull
    private Model model;
    @NotNull
    private Category category;
}
