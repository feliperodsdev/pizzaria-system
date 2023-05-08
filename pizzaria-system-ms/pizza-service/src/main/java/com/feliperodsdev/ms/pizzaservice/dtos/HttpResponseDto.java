package com.feliperodsdev.ms.pizzaservice.dtos;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HttpResponseDto {

    public HttpResponseDto(){}

    public <T> ResponseEntity<Object> badRequest(T data, HttpHeaders headers, MediaType mediaType){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .headers(headers)
                .contentType(mediaType)
                .body(new ResponseObjectDto<>(data, HttpStatus.BAD_REQUEST.value()));
    }

    public <T> ResponseEntity<Object> created(T data, HttpHeaders headers, MediaType mediaType){
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .contentType(mediaType)
                .body(new ResponseObjectDto<>(data, HttpStatus.CREATED.value()));
    }

    public <T> ResponseEntity<Object> ok(T data, HttpHeaders headers, MediaType mediaType){
        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .contentType(mediaType)
                .body(new ResponseObjectDto<>(data, HttpStatus.OK.value()));
    }

    public <T> ResponseEntity<Object> found(T data, HttpHeaders headers, MediaType mediaType){
        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(headers)
                .contentType(mediaType)
                .body(new ResponseObjectDto<>(data, HttpStatus.FOUND.value()));
    }

    public <T> ResponseEntity<Object> serverError(T data){
        return new ResponseEntity<>(new ResponseObjectDto<>(data, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
