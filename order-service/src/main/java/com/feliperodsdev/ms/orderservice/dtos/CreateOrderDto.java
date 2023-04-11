package com.feliperodsdev.ms.orderservice.dtos;

import java.util.List;

public class CreateOrderDto {

    private List<OrderItemDto> orderItemDtoList;

    public CreateOrderDto(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

}
