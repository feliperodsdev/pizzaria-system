package com.feliperodsdev.ms.financeservice.enums.exceptions;

public class InvalidCode extends RuntimeException {
    public InvalidCode(String msg){
        super(msg);
    }
}
