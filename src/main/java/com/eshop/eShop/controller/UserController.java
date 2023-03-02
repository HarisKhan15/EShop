package com.eshop.eShop.controller;

import com.eshop.eShop.dto.CategoryDto;
import com.eshop.eShop.dto.UserDto;
import com.eshop.eShop.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") @Min(value = 1) Long id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @GetMapping("/user/authentication/{email}/{password}")
    public ResponseEntity<Boolean> authentication(@PathVariable("email") String email,@PathVariable("password") String password){
        Boolean user = userService.authentication(email,password);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> save(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PatchMapping("/user/updateFields/{id}")
    private ResponseEntity<UserDto> updateProductByField(@PathVariable @Min(value = 1) Long id, @RequestBody Map<String,Object> fields){
        return ResponseEntity.ok(userService.updateByField(id,fields));
    }


}
