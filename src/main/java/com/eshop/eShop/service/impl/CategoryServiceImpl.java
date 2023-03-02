package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.domain.Coupon;
import com.eshop.eShop.dto.CategoryDto;
import com.eshop.eShop.exception.RecordAlreadyExistException;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.CategoryRepository;
import com.eshop.eShop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category dupCategory = categoryRepository.findByName(categoryDto.getName());
        if(dupCategory!=null){
            if(!dupCategory.getIsActive()){
                dupCategory.setIsActive(true);
                return toDto(categoryRepository.save(dupCategory));
            }
            throw new RecordAlreadyExistException(String.format("Record Already active => %s",categoryDto));
        }
        categoryDto.setIsActive(true);
        return toDto(categoryRepository.save(toDo(categoryDto)));
    }
    @Override
    public CategoryDto inActive(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            category.get().setIsActive(false);
            return toDto(categoryRepository.save(category.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }
    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAllByIsActive(Boolean.TRUE).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(Long id){
        Optional<Category> cat = categoryRepository.findById(id);
        if(cat.isPresent()){
            return toDto(cat.get());
        }else {
            throw new RecordNotFoundException(String.format("Category with that id %d doesn't exist!",id));
        }
    }

    @Override
    public CategoryDto updateByField(Long id, Map<String, Object> fields) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Category.class, key);
                field.setAccessible(Boolean.TRUE);
                ReflectionUtils.setField(field, category.get(), value);
            });
            return toDto(categoryRepository.save(category.get()));
        }
        throw new RecordNotFoundException(String.format("Record Not Found Against Id ==> %d",id));
    }

    @Override
    public List<CategoryDto> getBySearch(String name) {
        String sName = "%"+name+"%";
        return categoryRepository.findAllByIsActiveWithSearch(sName).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllWithInactive() {
        return categoryRepository.findAll().stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto activeCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            category.get().setIsActive(true);
            return toDto(categoryRepository.save(category.get()));
        }
        throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
    }

    public Category toDo(CategoryDto categoryDto){
        return modelMapper.map(categoryDto,Category.class);
    }
    public CategoryDto toDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
}
