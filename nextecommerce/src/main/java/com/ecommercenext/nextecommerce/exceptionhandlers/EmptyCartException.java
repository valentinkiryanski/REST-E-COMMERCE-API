package com.ecommercenext.nextecommerce.exceptionhandlers;

public class EmptyCartException extends RuntimeException{

    public EmptyCartException(String message){
        super(message);
    }

}
