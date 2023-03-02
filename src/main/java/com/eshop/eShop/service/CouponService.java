package com.eshop.eShop.service;

import com.eshop.eShop.dto.CouponDto;
import com.eshop.eShop.dto.ModelDto;


import java.util.List;
import java.util.Map;

public interface CouponService {
    CouponDto save(CouponDto couponDto);
    CouponDto inActive(Long id);
    List<CouponDto> getAll();
    CouponDto getById(Long id);
    CouponDto updateByField(Long id, Map<String, Object> fields);

    CouponDto updateCoupon(Long id, CouponDto couponDto);

    List<CouponDto> getBySearch(String name);

    List<CouponDto> getAllWithInActive();

    CouponDto activeCategory(Long id);
}
