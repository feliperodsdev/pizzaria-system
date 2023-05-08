package com.feliperodsdev.ms.financeservice.model.exceptions;

public class EntityValidationException extends RuntimeException {
    public EntityValidationException(String msg){
        super(msg);
    }
}
