package com.eshop.eShop.service;

import com.eshop.eShop.dto.CartDto;

import java.util.List;

public interface CartService {
    CartDto save(CartDto cartDto);
    List<CartDto> getaAll();
    CartDto getById(Long id);
}
