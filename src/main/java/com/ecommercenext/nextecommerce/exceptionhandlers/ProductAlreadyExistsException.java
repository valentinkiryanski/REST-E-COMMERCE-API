package com.ecommercenext.nextecommerce.exceptionhandlers;

public class ProductAlreadyExistsException extends RuntimeException{

    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
