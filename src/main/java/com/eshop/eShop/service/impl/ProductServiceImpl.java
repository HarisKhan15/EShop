package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Model;
import com.eshop.eShop.domain.Product;
import com.eshop.eShop.dto.ProductDto;
import com.eshop.eShop.exception.RecordAlreadyExistException;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.ProductRepository;
import com.eshop.eShop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        Product product = productRepository.findByNameAndCategoryAndModel(productDto.getName()
                ,productDto.getCategory()
                ,productDto.getModel());
        if(product!=null){
            if(!product.getIsActive()){
                product.setIsActive(true);
                return toDto(productRepository.save(product));
            }
            throw new RecordAlreadyExistException(String.format("Record Already active => %s",productDto.toString()));
        }
        productDto.setIsActive(Boolean.TRUE);
        return toDto(productRepository.save(toDo(productDto)));
    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAllByIsActive(Boolean.TRUE)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return toDto(product.get());
        }else{
            throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
        }
    }

    @Override
    public ProductDto inActive(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            product.get().setIsActive(false);
            return toDto(productRepository.save(product.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }

    @Override
    public ProductDto updateByField(Long id, Map<String, Object> fields) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Product.class, key);
                field.setAccessible(Boolean.TRUE);
                ReflectionUtils.setField(field, product.get(), value);
            });
            return toDto(productRepository.save(product.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().setModel(productDto.getModel());
            product.get().setName(productDto.getName());
            product.get().setPrice(productDto.getPrice());
            product.get().setCost(productDto.getCost());
            product.get().setStock(productDto.getStock());
            product.get().setImage(productDto.getImage());
            product.get().setCategory(productDto.getCategory());
            return toDto(productRepository.save(product.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }

    public Product toDo(ProductDto productDto){
        return modelMapper.map(productDto,Product.class);
    }
    public ProductDto toDto(Product product){
        return modelMapper.map(product,ProductDto.class);
    }
}
