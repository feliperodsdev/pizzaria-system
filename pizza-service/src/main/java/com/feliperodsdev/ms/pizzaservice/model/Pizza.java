package com.feliperodsdev.ms.pizzaservice.model;

import com.feliperodsdev.ms.pizzaservice.model.exceptions.EntityValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "pizzas")
public class Pizza {

    @Id
    private String id;
    private String name;
    private String desc;
    private Double price;

    public Pizza(){}

    public static Pizza Create(String name, String desc, double price){
        Pizza pizza = new Pizza();

        if(!pizza.isValidName(name)) {
            throw new EntityValidationException("'name' is invalid.");
        }

        if(!pizza.isValidPrice(price)){
            throw new EntityValidationException("'price' cannot be less than 0.");
        }

        if(!pizza.isValidDesc(desc)){
            throw new EntityValidationException("'desc' is invalid.");
        }

        pizza.price = price;
        pizza.desc = desc;
        pizza.name = name;
        return pizza;
    }

    public void updateName(String name){

        if(!isValidName(name)) {
            throw new EntityValidationException("'name' is invalid.");
        }

        this.name = name;
    }

    public void updatePrice(Double price){

        if(!isValidPrice(price)){
            throw new EntityValidationException("'price' cannot be less than 0.");
        }

        this.price = price;
    }

    public void updateDesc(String desc){

        if(!isValidDesc(desc)){
            throw new EntityValidationException("'desc' is invalid.");
        }

        this.desc = desc;
    }

    public Boolean isValidName(String name){
        name = name.trim();

        return !name.isEmpty() && name.length() <= 100;
    }

    public Boolean isValidPrice(Double price){
        return price >= 0;
    }

    public Boolean isValidDesc(String desc){
        desc = desc.trim();

        return !desc.isEmpty() && desc.length() <= 100;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        desc = desc.trim();
        return desc;
    }

    public Double getPrice() {
        return price;
    }
    public void setId(String id) {
        this.id = id;
    }
}
