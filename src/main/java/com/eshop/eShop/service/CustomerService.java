package com.eshop.eShop.service;

import com.eshop.eShop.dto.CustomerDto;
import com.eshop.eShop.dto.ModelDto;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    CustomerDto save(CustomerDto customerDto);
    List<CustomerDto> getAll();
    CustomerDto getById(Long id);
    CustomerDto updateByField(Long id, Map<String, Object> fields);
}
