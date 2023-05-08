package com.feliperodsdev.ms.financeservice.controllers.exceptions;

import com.feliperodsdev.ms.financeservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.financeservice.enums.exceptions.InvalidCode;
import com.feliperodsdev.ms.financeservice.model.exceptions.EntityValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.feliperodsdev.ms.financeservice.services.exceptions.ResourceNotFound;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityValidationException.class)
    public ResponseEntity<Object> entityValidationError(EntityValidationException e){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        String error = "Validation Error";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler(InvalidCode.class)
    public ResponseEntity<Object> invalidCodeError(InvalidCode e){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        String error = "Param Error";
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Object> notFound(ResourceNotFound e){
        HttpResponseDto response = new HttpResponseDto();
        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        String error = "Error";
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(statusCode, error, e.getMessage());
        return response.badRequest(standardError, headers, MediaType.APPLICATION_JSON);
    }

}
