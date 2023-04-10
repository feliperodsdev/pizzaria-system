package com.feliperodsdev.ms.orderservice.dtos;

public class CreatePizzaDto {

    private String pizza_id;
    private Double price;

    public CreatePizzaDto(String pizza_id, Double price) {
        this.pizza_id = pizza_id;
        this.price = price;
    }

    public String getPizza_id() {
        return pizza_id;
    }

    public void setPizza_id(String pizza_id) {
        this.pizza_id = pizza_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
