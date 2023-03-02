package com.eshop.eShop.controller;

import com.eshop.eShop.domain.Customer;
import com.eshop.eShop.dto.CategoryDto;
import com.eshop.eShop.dto.CustomerDto;
import com.eshop.eShop.dto.ProductDto;
import com.eshop.eShop.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDto>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.save(customerDto));
    }

    @PatchMapping("/customer/updateFields/{id}")
    private ResponseEntity<CustomerDto> updateProductByField(@PathVariable @Min(value = 1) Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(customerService.updateByField(id,fields));
    }
}
