package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Cart;
import com.eshop.eShop.domain.ProductCart;
import com.eshop.eShop.dto.ProductCartDto;
import com.eshop.eShop.repository.ProductCartRepository;
import com.eshop.eShop.service.ProductCartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCartServiceImpl implements ProductCartService {
    private final ProductCartRepository productCartRepository;
    private final ModelMapper modelMapper;

    public ProductCartServiceImpl(ProductCartRepository productCartRepository, ModelMapper modelMapper) {
        this.productCartRepository = productCartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductCartDto addIntoCart(ProductCartDto productCartDto) {
        return toDto(productCartRepository.save(toDo(productCartDto)));
    }

    @Override
    public List<ProductCartDto> getByCartId(Long id) {
        return productCartRepository.findByCartId(id).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductCartDto> getAll() {
        return productCartRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ProductCart toDo(ProductCartDto productCartDto){
        return modelMapper.map(productCartDto,ProductCart.class);
    }

    public ProductCartDto toDto(ProductCart productCart){
        return modelMapper.map(productCart,ProductCartDto.class);
    }
}
