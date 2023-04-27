package com.feliperodsdev.ms.pizzaservice.services.exceptions;

public class ServiceNotWorking extends RuntimeException {
    public ServiceNotWorking(){
        super("Some services are not working, try again later.");
    }
}
