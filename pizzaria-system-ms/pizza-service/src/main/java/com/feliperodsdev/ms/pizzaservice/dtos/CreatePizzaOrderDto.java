package com.feliperodsdev.ms.pizzaservice.dtos;

public class CreatePizzaOrderDto {

    private String pizzaId;
    private Double price;

    public CreatePizzaOrderDto(String pizzaId, Double price) {
        this.pizzaId = pizzaId;
        this.price = price;
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
