package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Customer;
import com.eshop.eShop.domain.Model;
import com.eshop.eShop.domain.User;
import com.eshop.eShop.dto.CouponDto;
import com.eshop.eShop.dto.UserDto;
import com.eshop.eShop.exception.RecordAlreadyExistException;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.UserRepository;
import com.eshop.eShop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public UserDto save(UserDto userDto) {
        User dupDto = userRepository.findByEmail(userDto.getEmail());
        if (dupDto != null) {
            if (!dupDto.getIsActive()) {
                dupDto.setIsActive(Boolean.TRUE);
                return toDto(userRepository.save(dupDto));
            }
            throw new RecordAlreadyExistException(String.format("Record already exist !"));
        }
        userDto.setIsActive(Boolean.TRUE);
        return toDto(userRepository.save(toDo(userDto)));
    }

    @Override
    public UserDto inActive(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setIsActive(false);
            return toDto(userRepository.save(user.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return toDto(user.get());
        }else {
            throw new RecordNotFoundException(String.format("Record doesn't exist against id ==> %d ",id));
        }
    }

    @Override
    public UserDto updateByField(Long id, Map<String, Object> fields) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, key);
                field.setAccessible(Boolean.TRUE);
                ReflectionUtils.setField(field, user.get(), value);
            });
            return toDto(userRepository.save(user.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }

    @Override
    public User toDo(UserDto userDto) {
        return modelMapper.map(userDto,User.class);
    }

    @Override
    public UserDto toDto(User user) {
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public Boolean authentication(String email, String password) {
        Optional<User> findUser = userRepository.findByEmailAndPassword(email,password);
        return findUser.isPresent() && findUser.get().getIsActive();
    }

}
