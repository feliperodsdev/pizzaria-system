package com.feliperodsdev.ms.financeservice.services.exceptions;

public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound(){
        super("Resource not found.");
    }
}
