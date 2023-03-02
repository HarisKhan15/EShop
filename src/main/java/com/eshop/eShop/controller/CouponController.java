package com.eshop.eShop.controller;

import com.eshop.eShop.dto.*;
import com.eshop.eShop.service.CouponService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/coupon/isactive")
    public ResponseEntity<List<CouponDto>> getAll(){
        return ResponseEntity.ok(couponService.getAll());
    }

    @GetMapping("/coupon")
    public ResponseEntity<List<CouponDto>> getAllWithInActive(){
        return ResponseEntity.ok(couponService.getAllWithInActive());
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<CouponDto> getById(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(couponService.getById(id));
    }
    @GetMapping("/coupon/search/{name}")
    public ResponseEntity<List<CouponDto>> getById(@PathVariable("name") @Min(value = 1) String name){
        return ResponseEntity.ok(couponService.getBySearch(name));
    }


    @PostMapping("/coupon")
    public ResponseEntity<CouponDto> save(@Valid @RequestBody CouponDto couponDto){
        return ResponseEntity.ok(couponService.save(couponDto));
    }
    @PutMapping("/coupon/update/{id}")
    public ResponseEntity<CouponDto> updateCoupon(@PathVariable @Min(value = 1) Long id, @Valid @RequestBody CouponDto couponDto){
        return ResponseEntity.ok(couponService.updateCoupon(id,couponDto));
    }

    @PatchMapping("/coupon/updateFields/{id}")
    private ResponseEntity<CouponDto> updateProductByField(@PathVariable @Min(value = 1) Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(couponService.updateByField(id,fields));
    }

    @PatchMapping("/coupon/inactive/{id}")
    public ResponseEntity<CouponDto> inActive(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(couponService.inActive(id));
    }

    @PatchMapping("/coupon/active/{id}")
    private ResponseEntity<CouponDto> activeCoupon(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(couponService.activeCategory(id));
    }
}