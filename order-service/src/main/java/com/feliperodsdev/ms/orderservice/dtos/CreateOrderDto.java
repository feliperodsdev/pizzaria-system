package com.feliperodsdev.ms.orderservice.dtos;

import java.util.List;

public class CreateOrderDto {

    private List<OrderItemDto> orderItemDtoList;

    public CreateOrderDto(){}

    public CreateOrderDto(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

    public void setOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }
}
