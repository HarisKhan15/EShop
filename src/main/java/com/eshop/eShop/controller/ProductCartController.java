package com.eshop.eShop.controller;

import com.eshop.eShop.dto.ProductCartDto;
import com.eshop.eShop.repository.ProductCartRepository;
import com.eshop.eShop.service.ProductCartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCartController {
    private final ProductCartService productCartService;

    public ProductCartController(ProductCartService productCartService) {
        this.productCartService = productCartService;
    }

    @GetMapping("/productcart/cart/{id}")
    public ResponseEntity<List<ProductCartDto>> getByCartId(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(productCartService.getByCartId(id));
    }
    @GetMapping("/productcart")
    public ResponseEntity<List<ProductCartDto>> getAll(){
        return ResponseEntity.ok(productCartService.getAll());
    }
    @PostMapping("/productcart")
    public ResponseEntity<ProductCartDto> save(@Valid @RequestBody ProductCartDto productCartDto){
        return ResponseEntity.ok(productCartService.addIntoCart(productCartDto));
    }


}
