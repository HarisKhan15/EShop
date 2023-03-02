package com.eshop.eShop.service.impl;

import com.eshop.eShop.domain.Transaction;
import com.eshop.eShop.dto.TransactionDto;
import com.eshop.eShop.exception.RecordNotFoundException;
import com.eshop.eShop.repository.TransactionRepository;
import com.eshop.eShop.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService  {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TransactionDto save(TransactionDto transactionDto) {
        transactionDto.setCheckOutDate(LocalDate.now());
        transactionDto.setTotalAmount(transactionRepository.getTotalAmount(transactionDto.getCart().getId()));
        return toDto(transactionRepository.save(toDo(transactionDto)));
    }

    @Override
    public List<TransactionDto> getAll() {
        return transactionRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public TransactionDto getById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isPresent()) {
            return toDto(transaction.get());
        }else {
            throw new RecordNotFoundException(String.format("Record not found against Id ==> %d",id));
        }
    }

    public Transaction toDo(TransactionDto transactionDto){
        return modelMapper.map(transactionDto,Transaction.class);
    }
    public TransactionDto toDto(Transaction transaction){
        return modelMapper.map(transaction,TransactionDto.class);
    }
}
