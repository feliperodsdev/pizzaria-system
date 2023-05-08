package com.feliperodsdev.ms.orderservice.model.exceptions;

public class EntityValidationException extends RuntimeException {
    public EntityValidationException(String msg){
        super(msg);
    }
}
