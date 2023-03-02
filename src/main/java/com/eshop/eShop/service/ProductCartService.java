package com.eshop.eShop.service;

import com.eshop.eShop.dto.ProductCartDto;

import java.util.List;

public interface ProductCartService {
    ProductCartDto addIntoCart(ProductCartDto productCartDto);
    List<ProductCartDto> getByCartId(Long id);

    List<ProductCartDto> getAll();
}
