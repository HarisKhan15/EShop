package com.eshop.eShop.controller;

import com.eshop.eShop.domain.Category;
import com.eshop.eShop.dto.CategoryDto;
import com.eshop.eShop.dto.CustomerDto;
import com.eshop.eShop.service.CategoryService;
import com.eshop.eShop.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/isactive")
    private ResponseEntity<List<CategoryDto>> getData(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping("/category")
    private ResponseEntity<List<CategoryDto>> getAll(){
        return ResponseEntity.ok(categoryService.getAllWithInactive());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(categoryService.getById(id));
    }
    @GetMapping("/category/search/{name}")
    public ResponseEntity<List<CategoryDto>> getById(@PathVariable("name") @Min(value = 1) String name){
        return ResponseEntity.ok(categoryService.getBySearch(name));
    }
    @PostMapping("/category")
    private ResponseEntity<CategoryDto> save(@Valid @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @PatchMapping("/category/updateFields/{id}")
    private ResponseEntity<CategoryDto> updateProductByField(@PathVariable @Min(value = 1) Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(categoryService.updateByField(id,fields));
    }

    @PatchMapping("/category/inactive/{id}")
    private ResponseEntity<CategoryDto> inActive(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(categoryService.inActive(id));
    }
    @PatchMapping("/category/active/{id}")
    private ResponseEntity<CategoryDto> activeCategory(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(categoryService.activeCategory(id));
    }


}
