package com.feliperodsdev.ms.orderservice.controllers;

import com.feliperodsdev.ms.orderservice.dtos.CreateOrderDto;
import com.feliperodsdev.ms.orderservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody CreateOrderDto createOrderDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"orderItemList"};

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        for (String field : requiredFields) {
            try {
                Field declaredField = createOrderDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(createOrderDto) == null) {
                    return response.badRequest("Missing Param: " + field, headers, MediaType.APPLICATION_JSON);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        return response.created(orderService.createOrder(createOrderDto), headers, MediaType.APPLICATION_JSON);
    }

    @GetMapping("/get-orders")
    public ResponseEntity<Object> getOrders(){
        HttpResponseDto response = new HttpResponseDto();

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        return response.ok(orderService.getOrders(), headers, MediaType.APPLICATION_JSON);
    }

}
