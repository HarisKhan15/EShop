package com.eshop.eShop.service;

import com.eshop.eShop.domain.Model;
import com.eshop.eShop.dto.ModelDto;
import com.eshop.eShop.dto.ProductDto;

import java.util.List;
import java.util.Map;

public interface ModelService {
    ModelDto save(ModelDto modelDto);
    ModelDto inActive(Long id);
    List<ModelDto> getAll();
    ModelDto getById(Long id);
    ModelDto updateByField(Long id, Map<String, Object> fields);

    List<ModelDto> getBySearch(String name);

    List<ModelDto> getAllWithInActive();

    ModelDto activeCategory(Long id);
}
