package com.feliperodsdev.ms.orderservice.services;

import com.feliperodsdev.ms.orderservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.orderservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.orderservice.model.Pizza;
import com.feliperodsdev.ms.orderservice.services.memory.InMemoryPizzaDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PizzaServiceTest {

    PizzaService pizzaService = new PizzaService(new InMemoryPizzaDB());
    //always will have at least 1 pizza in DB because
    // it was necessary to other tests

    @Test
    public void should_create_an_pizza() {
        CreatePizzaDto pizza = getPizza();
        pizzaService.createPizza(pizza);
        Assertions.assertEquals(2, pizzaService.getAllPizzas().size());
    }

    @Test
    public void should_return_pizzas() {
        List<Pizza> pizzaList = pizzaService.getAllPizzas();
        Assertions.assertEquals(1, pizzaList.size());
    }

    @Test
    public void should_update_pizza() {
        CreatePizzaDto pizzaCreate = getPizza();
        pizzaService.createPizza(pizzaCreate);
        List<Pizza> pizzaList = pizzaService.getAllPizzas();
        Pizza pizza = pizzaService.getPizzaById(pizzaList.get(0).getPizzaId());
        UpdatePizzaDto updatePizzaDto = new UpdatePizzaDto(20.0);
        pizzaService.updatePizza(pizza.getPizzaId(), updatePizzaDto);
        Pizza pizzaUpdated = pizzaService.getPizzaById(pizzaList.get(0).getPizzaId());
        Assertions.assertEquals(pizzaUpdated.getPrice(), 20.0);
    }

    @Test
    public void should_delete_pizza(){
        pizzaService.createPizza(getPizza());
        Pizza pizza = pizzaService.getPizzaById(getPizza().getPizzaId());
        Assertions.assertEquals(getPizza().getPizzaId(), pizza.getPizzaId());
        pizzaService.deletePizzaById("6425b05a66bb0e2c94fbac9f");
        Assertions.assertEquals(1, pizzaService.getAllPizzas().size());
        //ensure that the pizza in the db is a different one
        //as we will need that for other tests.
        Assertions.assertEquals("valid id.", pizzaService
                .getPizzaById("valid id.")
                .getPizzaId());
    }

    public CreatePizzaDto getPizza(){
        return new CreatePizzaDto("6425b05a66bb0e2c94fbac9f",15.5);
    }

}
