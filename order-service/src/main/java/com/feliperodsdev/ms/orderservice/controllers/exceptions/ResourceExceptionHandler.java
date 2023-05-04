package com.feliperodsdev.ms.orderservice.controllers.exceptions;

import com.feliperodsdev.ms.orderservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;
import com.feliperodsdev.ms.orderservice.services.exceptions.ResourceAlreadyExists;
import com.feliperodsdev.ms.orderservice.services.exceptions.ResourceNotFound;
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
    public ResponseEntity<Object> entityValidationError(EntityValidationException e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Validation Error";
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<Object> alreadyExist(ResourceAlreadyExists e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Already found";
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        HttpStatus statusCode = HttpStatus.FOUND;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Object> notFound(ResourceNotFound e){
        HttpResponseDto response = new HttpResponseDto();
        String error = "Error";
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

}
