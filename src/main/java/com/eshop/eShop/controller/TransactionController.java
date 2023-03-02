package com.eshop.eShop.controller;

import com.eshop.eShop.dto.TransactionDto;
import com.eshop.eShop.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionDto>> getAll(){
        return ResponseEntity.ok(transactionService.getAll());
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionDto> getById(@PathVariable @Min(value = 1) Long id){
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @PostMapping("/transaction")
    public ResponseEntity<TransactionDto> save(@Valid @RequestBody TransactionDto transactionDto){
        return ResponseEntity.ok(transactionService.save(transactionDto));
    }
}
