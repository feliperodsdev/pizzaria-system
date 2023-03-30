package com.feliperodsdev.ms.pizzaservice.model;

import com.feliperodsdev.ms.pizzaservice.model.exceptions.EntitieValidationException;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "pizzas")
public class Pizza {

    @Id
    private String id;
    private String name;
    private String desc;
    private BigDecimal price;

    public Pizza(){}

    public static Pizza Create(String name, String desc, double price){
        Pizza pizza = new Pizza();

        if(!pizza.isValidName(name)) {
            throw new EntitieValidationException("'name' is invalid.");
        }

        if(!pizza.isValidPrice(price)){
            throw new EntitieValidationException("'price' cannot be less than 0.");
        }

        if(!pizza.isValidDesc(desc)){
            throw new EntitieValidationException("'desc' is invalid.");
        }

        pizza.price = BigDecimal.valueOf(price);
        pizza.desc = desc;
        pizza.name = name;
        return pizza;
    }

    public void UpdateName(String name){

        if(!isValidName(name)) {
            throw new EntitieValidationException("'name' is invalid.");
        }

        this.name = name;
    }

    public void UpdatePrice(Double price){

        if(!isValidPrice(price)){
            throw new EntitieValidationException("'price' cannot be less than 0.");
        }

        this.price = BigDecimal.valueOf(price);
    }

    public void UpdateDesc(String desc){

        if(!isValidDesc(desc)){
            throw new EntitieValidationException("'desc' is invalid.");
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

    public BigDecimal getPrice() {
        return price;
    }
    public void setId(String id) {
        this.id = id;
    }
}
