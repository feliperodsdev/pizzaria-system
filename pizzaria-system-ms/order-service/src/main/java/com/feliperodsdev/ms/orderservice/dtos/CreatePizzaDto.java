package com.feliperodsdev.ms.orderservice.dtos;

public class CreatePizzaDto {

    private String pizzaId;
    private Double price;

    public CreatePizzaDto(String pizzaId, Double price) {
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
