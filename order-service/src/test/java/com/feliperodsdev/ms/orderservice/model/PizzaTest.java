package com.feliperodsdev.ms.orderservice.model;

import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PizzaTest {

    @Test
    public void should_create_pizza(){
        Pizza pizza = Pizza.Create("6425b05a66bb0e2c94fbac9f",15.5);

        assertEquals(pizza.getPrice().equals(15.5), true);
    }

    @Test
    public void should_throw_an_error_id(){
        EntityValidationException entitieValidationException = assertThrows(EntityValidationException.class,
                this::createPizzaErrorId);

        assertEquals("'id' is invalid.", entitieValidationException.getMessage());
    }

    @Test
    public void should_throw_an_error_price(){

        EntityValidationException entitieValidationException = assertThrows(EntityValidationException.class,
                this::createPizzaErrorPrice);

        assertEquals("'price' is invalid.", entitieValidationException.getMessage());
    }

    @Test
    public void should_thrown_error_update_price(){
        Pizza pizza = createPizza();

        EntityValidationException entitieValidationException = assertThrows(EntityValidationException.class,
                this::updatePizzaError);

        assertEquals("'price' cannot be less than 0.", entitieValidationException.getMessage());

    }

    public Pizza createPizza(){
        return Pizza.Create("6425b05a66bb0e2c94fbac9f", 15.5);
    }

    public void createPizzaErrorId(){
        Pizza.Create("", 15.5);
    }

    public void createPizzaErrorPrice(){
        Pizza.Create("6425b05a66bb0e2c94fbac9f", -4.0);
    }

    public void updatePizzaError(){
        Pizza pizza = createPizza();

        pizza.updatePrice(-4.5);

    }

}
