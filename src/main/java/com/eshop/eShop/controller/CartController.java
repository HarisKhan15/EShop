package com.eshop.eShop.controller;

import com.eshop.eShop.dto.CartDto;
import com.eshop.eShop.service.CartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public ResponseEntity<List<CartDto>> getAll(){
        return ResponseEntity.ok(cartService.getaAll());
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<CartDto> getById(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(cartService.getById(id));
    }
    @PostMapping("/cart")
    public ResponseEntity<CartDto> save(@Valid @RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.save(cartDto));
    }

}
