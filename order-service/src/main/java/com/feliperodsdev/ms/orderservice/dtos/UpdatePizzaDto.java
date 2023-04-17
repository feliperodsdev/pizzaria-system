package com.feliperodsdev.ms.orderservice.dtos;

public class UpdatePizzaDto {

    private Double price;

    public UpdatePizzaDto(Double price) {
        this.price = price;
    }

    public UpdatePizzaDto() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
