package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.domain.Coupon;
import com.eshop.eShop.domain.Customer;
import com.eshop.eShop.domain.Product;
import com.eshop.eShop.dto.CouponDto;
import com.eshop.eShop.exception.RecordAlreadyExistException;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.CouponRepository;
import com.eshop.eShop.service.CouponService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final ModelMapper modelMapper;

    public CouponServiceImpl(CouponRepository couponRepository, ModelMapper modelMapper) {
        this.couponRepository = couponRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CouponDto save(CouponDto couponDto) {
        Coupon dupCoupon = couponRepository.findByCouponCode(couponDto.getCouponCode());
        if(dupCoupon!=null){
            if(!dupCoupon.getIsActive()){
                dupCoupon.setIsActive(true);
                dupCoupon.setDiscount(couponDto.getDiscount());
                dupCoupon.setIsPercentage(couponDto.getIsPercentage());
                return toDto(couponRepository.save(dupCoupon));
            }
            throw new RecordAlreadyExistException(String.format("Record Already active => %s",dupCoupon));
        }
        couponDto.setIsActive(true);
        return toDto(couponRepository.save(toDo(couponDto)));
    }

    @Override
    public CouponDto inActive(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if(coupon.isPresent()){
            coupon.get().setIsActive(false);
            return toDto(couponRepository.save(coupon.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }

    @Override
    public List<CouponDto> getAll() {
        return couponRepository.findAllByIsActive(Boolean.TRUE).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CouponDto getById(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if(coupon.isPresent()){
            return toDto(coupon.get());
        }else {
            throw new RecordNotFoundException(String.format("Record not found against Id ==> %d ",id));
        }
    }

    @Override
    public CouponDto updateByField(Long id, Map<String, Object> fields) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if (coupon.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Coupon.class, key);
                field.setAccessible(Boolean.TRUE);
                ReflectionUtils.setField(field, coupon.get(), value);
            });
            return toDto(couponRepository.save(coupon.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }

    @Override
    public CouponDto updateCoupon(Long id, CouponDto couponDto) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if (coupon.isPresent()) {
            coupon.get().setCouponCode(couponDto.getCouponCode());
            coupon.get().setDiscount(couponDto.getDiscount());
            coupon.get().setIsPercentage(couponDto.getIsPercentage());
            return toDto(couponRepository.save(coupon.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }

    @Override
    public List<CouponDto> getBySearch(String name) {
        String sName = "%"+name+"%";
        return couponRepository.findAllByIsActiveWithSearch(sName).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CouponDto> getAllWithInActive() {
        return couponRepository.findAll().stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CouponDto activeCategory(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        if(coupon.isPresent()){
            coupon.get().setIsActive(true);
            return toDto(couponRepository.save(coupon.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }

    public Coupon toDo(CouponDto couponDto){
        return modelMapper.map(couponDto,Coupon.class);
    }
    public CouponDto toDto(Coupon coupon){
        return modelMapper.map(coupon,CouponDto.class);
    }
}
