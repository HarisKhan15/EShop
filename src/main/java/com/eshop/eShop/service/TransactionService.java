package com.eshop.eShop.service;

import com.eshop.eShop.dto.ProductDto;
import com.eshop.eShop.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto save(TransactionDto transactionDto);
    List<TransactionDto> getAll();
    TransactionDto getById(Long id);
}
