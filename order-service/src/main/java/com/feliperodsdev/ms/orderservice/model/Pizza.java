package com.feliperodsdev.ms.orderservice.model;

import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    private String pizza_id;
    private BigDecimal price;

    public Pizza(){}

    public static Pizza Create(String id, Double price){
        Pizza pizza = new Pizza();

        if(!pizza.isValidId(id)){
            throw new EntityValidationException("'id' is invalid.");
        }

        pizza.pizza_id = id;

        if(!pizza.isValidPrice(price)){
            throw new EntityValidationException("'price' is invalid.");
        }

        pizza.price = BigDecimal.valueOf(price);

        return pizza;

    }

    public Boolean isValidId(String id){
        id = id.trim();

        return !id.isEmpty();
    }

    public Boolean isValidPrice(Double price){
        return price >= 0;
    }

    public void updatePrice(Double price){

        if(!isValidPrice(price)){
            throw new EntityValidationException("'price' cannot be less than 0.");
        }

        this.price = BigDecimal.valueOf(price);
    }

    public String getPizza_id() {
        return pizza_id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
