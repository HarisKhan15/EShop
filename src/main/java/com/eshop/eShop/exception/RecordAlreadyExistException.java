package com.eshop.eShop.exception;

public class RecordAlreadyExistException extends RuntimeException{
    public RecordAlreadyExistException(String message){
        super(message);
    }
}
