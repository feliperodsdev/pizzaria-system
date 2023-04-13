package com.feliperodsdev.ms.orderservice.controllers;

import com.feliperodsdev.ms.orderservice.dtos.CreateOrderDto;
import com.feliperodsdev.ms.orderservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody CreateOrderDto createOrderDto){
        HttpResponseDto response = new HttpResponseDto();
        return response.created(orderService.createOrder(createOrderDto));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getOrders(){
        HttpResponseDto response = new HttpResponseDto();
        return response.ok(orderService.getOrders());
    }

}
