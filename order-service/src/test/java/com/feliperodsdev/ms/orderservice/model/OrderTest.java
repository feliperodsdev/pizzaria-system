package com.feliperodsdev.ms.orderservice.model;

import com.feliperodsdev.ms.orderservice.dtos.CreateOrderDto;
import com.feliperodsdev.ms.orderservice.dtos.OrderItemDto;
import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class OrderTest {

    @Test
    public void should_create_an_order(){
        List<OrderItemDto> itemDtos = new ArrayList<>();
        OrderItemDto orderItemDto = getOrderItemDto();
        itemDtos.add(orderItemDto);
        Order order = Order.create(mapListOrderItem(getOrderDto(itemDtos).getOrderItemDtoList()));
        Assertions.assertEquals(order.getPayment_status(), false);
        Assertions.assertEquals(order.getOrder_item_list().get(0).getPizza_id(), "valid id.");
    }

    @Test
    public void should_thrown_an_error(){
        EntityValidationException entityValidationException = assertThrows(EntityValidationException.class,
                this::createInvalidOrder);

        assertEquals("Order cannot be placed without product(s).", entityValidationException.getMessage());
    }

    public CreateOrderDto getOrderDto(List<OrderItemDto> list){
        return new CreateOrderDto(list);
    }

    public List<OrderItem> mapListOrderItem(List<OrderItemDto> list){
        int size = list.size();
        List<OrderItem> orderItemList = new ArrayList<>();
        for(int i = 0; i<size; ++i){
            OrderItem orderItem = OrderItem.create(list.get(i).getPizza_id(),
                    151.1,
                    list.get(i).getDiscount());
            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    public OrderItemDto getOrderItemDto(){
        return new OrderItemDto("valid id.", 10.0);
    }

    public void createInvalidOrder(){
        List<OrderItemDto> itemDtos = new ArrayList<>();
        Order.create(mapListOrderItem(getOrderDto(itemDtos).getOrderItemDtoList()));
    }

}
