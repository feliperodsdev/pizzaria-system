package com.feliperodsdev.ms.financeservice.dtos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpResponseDto {

    public HttpResponseDto(){}

    public <T> ResponseEntity<Object> badRequest(T data){
        return new ResponseEntity<>(new ResponseObjectDto<>(data, HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
    }

    public <T> ResponseEntity<Object> created(T data){
        return new ResponseEntity<>(new ResponseObjectDto<>(data, HttpStatus.CREATED.value()),HttpStatus.CREATED);
    }

    public <T> ResponseEntity<Object> serverError(T data){
        return new ResponseEntity<>(new ResponseObjectDto<>(data, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public <T> ResponseEntity<Object> ok(T data){
        return new ResponseEntity<>(new ResponseObjectDto<>(data, HttpStatus.OK.value()),HttpStatus.OK);
    }

    public <T> ResponseEntity<Object> found(T data){
        return new ResponseEntity<>(new ResponseObjectDto<>(data, HttpStatus.FOUND.value()),HttpStatus.FOUND);
    }

}
