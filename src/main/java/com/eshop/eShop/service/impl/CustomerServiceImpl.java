package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Customer;
import com.eshop.eShop.domain.Model;
import com.eshop.eShop.domain.Product;
import com.eshop.eShop.dto.CustomerDto;
import com.eshop.eShop.exception.RecordAlreadyExistException;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.CustomerRepository;
import com.eshop.eShop.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer= customerRepository.findByEmail(customerDto.getEmail());
        if(customer==null){
            customerDto.setIsActive(Boolean.TRUE);
            return toDto(customerRepository.save(toDo(customerDto)));
        }
        throw new RecordAlreadyExistException(String.format("This Email is already registered !"));
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return toDto(customer.get());
        }else {
            throw new RecordNotFoundException(String.format("Category with that id %d doesn't exist!",id));
        }
    }

    @Override
    public CustomerDto updateByField(Long id, Map<String, Object> fields) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Customer.class, key);
                field.setAccessible(Boolean.TRUE);
                ReflectionUtils.setField(field, customer.get(), value);
            });
            return toDto(customerRepository.save(customer.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }


    public Customer toDo(CustomerDto customerDto){
        return modelMapper.map(customerDto,Customer.class);
    }
    public CustomerDto toDto(Customer customer){
        return modelMapper.map(customer,CustomerDto.class);
    }
}
