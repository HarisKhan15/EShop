package com.eshop.eShop.service;

import com.eshop.eShop.domain.User;
import com.eshop.eShop.dto.CouponDto;
import com.eshop.eShop.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto inActive(Long id);
    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto updateByField(Long id, Map<String, Object> fields);

    User toDo(UserDto userDto);
    UserDto toDto(User user);

    Boolean authentication(String email, String password);
}
