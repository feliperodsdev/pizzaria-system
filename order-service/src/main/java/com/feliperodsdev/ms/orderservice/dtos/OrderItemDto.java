package com.feliperodsdev.ms.orderservice.dtos;

public class OrderItemDto {

    private String pizza_id;
    private Double discount;

    public OrderItemDto(String pizza_id, Double discount) {
        this.pizza_id = pizza_id;
        this.discount = discount;
    }

    public String getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(String pizza_id) {
        this.pizza_id = pizza_id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
