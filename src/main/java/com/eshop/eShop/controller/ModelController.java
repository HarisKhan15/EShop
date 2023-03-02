package com.eshop.eShop.controller;


import com.eshop.eShop.domain.Model;
import com.eshop.eShop.dto.CategoryDto;
import com.eshop.eShop.dto.ModelDto;
import com.eshop.eShop.dto.ProductDto;
import com.eshop.eShop.service.ModelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ModelController {
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/model/isactive")
    private ResponseEntity<List<ModelDto>> getData(){
        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/model")
    private ResponseEntity<List<ModelDto>> getAll(){
        return ResponseEntity.ok(modelService.getAllWithInActive());
    }

    @GetMapping("/model/{id}")
    public ResponseEntity<ModelDto> getById(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(modelService.getById(id));
    }
    @GetMapping("/model/search/{name}")
    public ResponseEntity<List<ModelDto>> getById(@PathVariable("name") @Min(value = 1) String name){
        return ResponseEntity.ok(modelService.getBySearch(name));
    }

    @PostMapping("/model")
    public ResponseEntity<ModelDto> save(@Valid @RequestBody ModelDto modelDto){
        return ResponseEntity.ok(modelService.save(modelDto));
    }

    @PatchMapping("/model/updateFields/{id}")
    private ResponseEntity<ModelDto> updateProductByField(@PathVariable @Min(value = 1) Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(modelService.updateByField(id,fields));
    }
    @PatchMapping("/model/inactive/{id}")
    private ResponseEntity<ModelDto> inActive(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(modelService.inActive(id));
    }
    @PatchMapping("/model/active/{id}")
    private ResponseEntity<ModelDto> activeModel(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(modelService.activeCategory(id));
    }
}
