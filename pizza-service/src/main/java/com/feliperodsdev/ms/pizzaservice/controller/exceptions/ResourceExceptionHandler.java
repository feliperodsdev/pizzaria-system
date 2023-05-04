package com.feliperodsdev.ms.pizzaservice.controller.exceptions;

import com.feliperodsdev.ms.pizzaservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.pizzaservice.model.exceptions.EntityValidationException;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ResouceNotFound;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ServiceNotWorking;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<Object> entitieValidationError(EntityValidationException e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Validation Error";
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler(ResouceNotFound.class)
    public ResponseEntity<Object> resourceNotFound(ResouceNotFound e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Resource Not Found";
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler(ServiceNotWorking.class)
    public ResponseEntity<Object> serviceNotWorking(ServiceNotWorking e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Service not working.";
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

}
