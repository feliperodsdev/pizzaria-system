package com.feliperodsdev.ms.orderservice.controllers.exceptions;

import com.feliperodsdev.ms.orderservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;
import com.feliperodsdev.ms.orderservice.services.exceptions.ResourceAlreadyExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<Object> entityValidationError(EntityValidationException e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Validation Error";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<Object> alreadyExist(ResourceAlreadyExists e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Error";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError);
    }

}
