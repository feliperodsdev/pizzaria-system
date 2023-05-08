package com.feliperodsdev.ms.orderservice.dtos;

import java.util.List;

public class CreateOrderDto {

    private List<OrderItemDto> orderItemList;

    public CreateOrderDto(){}

    public CreateOrderDto(List<OrderItemDto> orderItemDtoList) {
        this.orderItemList = orderItemDtoList;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemList;
    }

}
