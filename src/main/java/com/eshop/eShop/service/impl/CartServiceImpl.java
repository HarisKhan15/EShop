package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Cart;
import com.eshop.eShop.dto.CartDto;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.CartRepository;
import com.eshop.eShop.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CartDto save(CartDto cartDto) {
        cartDto.setCreateDate(LocalDate.now());
        return toDto(cartRepository.save(toDo(cartDto)));
    }

    @Override
    public List<CartDto> getaAll() {
        return cartRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CartDto getById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return toDto(cart.get());
        }else{
            throw new RecordNotFoundException(String.format("Record not found against id ==> %d",id));
        }
    }

    public Cart toDo(CartDto cartDto){
        return modelMapper.map(cartDto,Cart.class);
    }
    public CartDto toDto(Cart cart){
        return modelMapper.map(cart,CartDto.class);
    }
}
