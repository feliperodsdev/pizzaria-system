package com.feliperodsdev.ms.pizzaservice.dtos;

public class UpdateOrderPizzaDto {

    private Double price;

    public UpdateOrderPizzaDto(Double price) {
        this.price = price;
    }

    public UpdateOrderPizzaDto() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
