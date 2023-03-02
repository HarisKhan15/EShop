package com.eshop.eShop.repository;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.domain.Coupon;
import com.eshop.eShop.dto.CouponDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<Coupon> findAllByIsActive(Boolean isActive);
    Coupon findByCouponCode(String couponCode);

    @Query(value = "select * from coupon where is_active = 1 and coupon_code like :name",nativeQuery = true)
    List<Coupon> findAllByIsActiveWithSearch(String name);
}
