package com.feliperodsdev.ms.pizzaservice.controller.exceptions;

import com.feliperodsdev.ms.pizzaservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.pizzaservice.model.exceptions.EntityValidationException;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ResouceNotFound;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ServiceNotWorking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<Object> entitieValidationError(EntityValidationException e){
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

    @ExceptionHandler(ServiceNotWorking.class)
    public ResponseEntity<Object> serviceNotWorking(ServiceNotWorking e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Service not working.";
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError);
    }

}
