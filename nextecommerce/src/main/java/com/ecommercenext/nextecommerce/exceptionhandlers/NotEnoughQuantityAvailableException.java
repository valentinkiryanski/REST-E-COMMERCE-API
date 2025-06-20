package com.ecommercenext.nextecommerce.exceptionhandlers;

public class NotEnoughQuantityAvailableException extends RuntimeException{

    public NotEnoughQuantityAvailableException(String message){
        super(message);
    }
}
