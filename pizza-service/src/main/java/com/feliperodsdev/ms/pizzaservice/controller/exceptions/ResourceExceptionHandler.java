package com.feliperodsdev.ms.pizzaservice.controller.exceptions;

import com.feliperodsdev.ms.pizzaservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.pizzaservice.model.exceptions.EntitieValidationException;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ResouceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntitieValidationException.class)
    public ResponseEntity<Object> entitieValidationError(EntitieValidationException e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Validation Error";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError);
    }

    @ExceptionHandler(ResouceNotFound.class)
    public ResponseEntity<Object> resourceNotFound(ResouceNotFound e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Resource Not Found";
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError);
    }

}
