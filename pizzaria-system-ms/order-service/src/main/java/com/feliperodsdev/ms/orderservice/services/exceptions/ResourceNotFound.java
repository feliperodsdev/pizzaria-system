package com.feliperodsdev.ms.orderservice.services.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String msg){
        super(msg);
    }

}
