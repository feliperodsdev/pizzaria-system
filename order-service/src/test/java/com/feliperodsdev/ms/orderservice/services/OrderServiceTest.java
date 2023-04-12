package com.feliperodsdev.ms.orderservice.services;

import com.feliperodsdev.ms.orderservice.dtos.CreateOrderDto;
import com.feliperodsdev.ms.orderservice.dtos.OrderItemDto;
import com.feliperodsdev.ms.orderservice.services.memory.InMemoryOrderDB;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {

    private OrderService orderService = new OrderService(new InMemoryOrderDB());

    @Test
    public void should_create_an_order(){
        List<OrderItemDto> list = new ArrayList<>();
        list.add(getOrderItemDto());
        CreateOrderDto createOrderDto = getOrderDto(list);
        orderService.createOrder(createOrderDto);
    }

    public OrderItemDto getOrderItemDto(){
        return new OrderItemDto("valid id.", 10.0);
    }

    public CreateOrderDto getOrderDto(List<OrderItemDto> list){
        return new CreateOrderDto(list);
    }

}
