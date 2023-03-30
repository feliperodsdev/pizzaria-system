package com.feliperodsdev.ms.pizzaservice.model.exceptions;

public class EntitieValidationException extends RuntimeException {
    public EntitieValidationException(String msg){
        super(msg);
    }
}
