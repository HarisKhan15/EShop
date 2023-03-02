package com.eshop.eShop.service;

import com.eshop.eShop.dto.ModelDto;
import com.eshop.eShop.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    ProductDto inActive(Long id);
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto updateByField(Long id, Map<String, Object> fields);

    ProductDto updateProduct(Long id, ProductDto productDto);
}
