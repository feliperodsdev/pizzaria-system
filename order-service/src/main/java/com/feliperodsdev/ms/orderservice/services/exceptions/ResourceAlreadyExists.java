package com.feliperodsdev.ms.orderservice.services.exceptions;

public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(String msg){
        super(msg);
    }

}
