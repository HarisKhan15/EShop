package com.eshop.eShop.controller;

import com.eshop.eShop.dto.ModelDto;
import com.eshop.eShop.dto.ProductDto;
import com.eshop.eShop.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> save(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.save(productDto));
    }

    @PutMapping("/product/updateAll/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable @Min(value = 1) Long id,@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productService.updateProduct(id,productDto));
    }

    @PatchMapping("/product/updateFields/{id}")
    private ResponseEntity<ProductDto> updateProductByField(@PathVariable @Min(value = 1) Long id,@RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(productService.updateByField(id,fields));
    }

    @PatchMapping("/product/inactive/{id}")
    private ResponseEntity<ProductDto> inActive(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(productService.inActive(id));
    }
}
