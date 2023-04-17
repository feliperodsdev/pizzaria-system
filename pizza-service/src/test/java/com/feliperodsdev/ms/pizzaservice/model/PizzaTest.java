package com.feliperodsdev.ms.pizzaservice.model;

import com.feliperodsdev.ms.pizzaservice.model.exceptions.EntityValidationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PizzaTest {

    @Test
    public void should_create_pizza(){
        Pizza pizza = Pizza.Create("Calabresa", "One of the best!", 15.5);
        assertEquals(pizza.getName(), "Calabresa");
        assertEquals(pizza.getDesc(), "One of the best!");
        assertEquals(pizza.getPrice(), BigDecimal.valueOf(15.5));
    }

    @Test
    public void should_throw_an_error_name(){

        Pizza pizza = CreatePizza();

        EntityValidationException entitieValidationException = assertThrows(EntityValidationException.class,
                this::CreatePizzaErrorName);

        assertEquals("'name' is invalid.", entitieValidationException.getMessage());
        assertEquals(false, pizza.isValidName(" "));
    }

    @Test
    public void should_throw_an_error_desc(){

        Pizza pizza = CreatePizza();

        EntityValidationException entitieValidationException = assertThrows(EntityValidationException.class,
                this::CreatePizzaErrorDesc);

        assertEquals("'desc' is invalid.", entitieValidationException.getMessage());
        assertEquals(false, pizza.isValidDesc(" "));
    }

    @Test
    public void should_throw_an_error_price(){

        Pizza pizza = CreatePizza();

        EntityValidationException entitieValidationException = assertThrows(EntityValidationException.class,
                this::CreatePizzaErrorPrice);

        assertEquals("'price' cannot be less than 0.", entitieValidationException.getMessage());
        assertEquals(false, pizza.isValidPrice(-4.5));
    }

    public Pizza CreatePizza(){
        return Pizza.Create("Calabresa", "One of the best!", 15.5);
    }

    public void CreatePizzaErrorName(){
        Pizza.Create("", "One of the best!", 15.5);
    }

    public void CreatePizzaErrorDesc(){
        Pizza.Create("Calabresa", "", 15.5);
    }

    public void CreatePizzaErrorPrice(){
        Pizza.Create("Calabresa", "One of the best!", -4);
    }

}
