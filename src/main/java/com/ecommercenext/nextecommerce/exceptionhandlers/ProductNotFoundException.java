package com.ecommercenext.nextecommerce.exceptionhandlers;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message){
        super(message);
    }

}
