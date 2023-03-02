package com.eshop.eShop.service;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.dto.CartDto;
import com.eshop.eShop.dto.CategoryDto;
import com.eshop.eShop.dto.CouponDto;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);
    CategoryDto inActive(Long id);
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    CategoryDto updateByField(Long id, Map<String, Object> fields);

    List<CategoryDto> getBySearch(String name);

    List<CategoryDto> getAllWithInactive();

    CategoryDto activeCategory(Long id);
}
