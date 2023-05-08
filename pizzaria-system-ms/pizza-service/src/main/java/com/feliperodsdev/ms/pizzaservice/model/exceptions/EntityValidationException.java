package com.feliperodsdev.ms.pizzaservice.model.exceptions;

public class EntityValidationException extends RuntimeException {
    public EntityValidationException(String msg){
        super(msg);
    }
}
