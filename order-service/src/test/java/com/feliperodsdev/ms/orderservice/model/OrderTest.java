package com.feliperodsdev.ms.orderservice.model;

import com.feliperodsdev.ms.orderservice.dtos.CreateOrderDto;
import com.feliperodsdev.ms.orderservice.dtos.OrderItemDto;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {

    @Test
    public void should_create_an_order(){
        Order order = Order.create(getListOrderItem(getOrderDto().getOrderItemDtoList()));
        Assertions.assertEquals(order.getPayment_status(), false);
    }

    public CreateOrderDto getOrderDto(){
        List<OrderItemDto> itemDtos = new ArrayList<>();
        OrderItemDto orderItemDto = getOrderItemDto();
        itemDtos.add(orderItemDto);
        return new CreateOrderDto(itemDtos);
    }

    public List<OrderItem> getListOrderItem(List<OrderItemDto> list){
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

}
