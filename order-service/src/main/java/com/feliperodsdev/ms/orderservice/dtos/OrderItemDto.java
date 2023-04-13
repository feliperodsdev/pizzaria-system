package com.feliperodsdev.ms.orderservice.dtos;

public class OrderItemDto {

    private String pizzaId;
    private Double discount;

    public OrderItemDto() {
    }

    public OrderItemDto(String pizzaId, Double discount) {
        this.pizzaId = pizzaId;
        this.discount = discount;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizza_id) {
        this.pizzaId = pizza_id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
