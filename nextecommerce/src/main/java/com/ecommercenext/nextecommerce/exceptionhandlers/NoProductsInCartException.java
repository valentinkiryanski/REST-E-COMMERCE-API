package com.ecommercenext.nextecommerce.exceptionhandlers;

public class NoProductsInCartException extends RuntimeException{

    public NoProductsInCartException(String message){
        super(message);
    }
}
