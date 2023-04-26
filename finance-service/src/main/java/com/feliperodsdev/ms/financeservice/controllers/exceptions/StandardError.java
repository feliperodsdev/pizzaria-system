package com.feliperodsdev.ms.financeservice.controllers.exceptions;

import org.springframework.http.HttpStatus;

public class StandardError {

    private HttpStatus statusCode;
    private String error;
    private String message;

    public StandardError(HttpStatus statusCode, String error, String message) {
        super();
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
