package com.feliperodsdev.ms.pizzaservice.services.exceptions;

public class ResouceNotFound extends RuntimeException {
    public ResouceNotFound(String id){
        super("Resource not found, id: " + id);
    }
}
