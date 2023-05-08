package com.feliperodsdev.ms.orderservice.services;

import com.feliperodsdev.ms.orderservice.dtos.CreateOrderDto;
import com.feliperodsdev.ms.orderservice.dtos.OrderItemDto;
import com.feliperodsdev.ms.orderservice.model.Pizza;
import com.feliperodsdev.ms.orderservice.services.memory.InMemoryOrderDB;
import com.feliperodsdev.ms.orderservice.services.memory.InMemoryPizzaDB;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {

    OrderService orderService = new OrderService(new InMemoryOrderDB(),
            new InMemoryPizzaDB());

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
