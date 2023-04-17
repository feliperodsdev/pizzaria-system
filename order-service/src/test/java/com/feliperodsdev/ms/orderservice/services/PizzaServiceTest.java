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

    @Test
    public void should_create_an_pizza() {
        CreatePizzaDto pizza = GetPizza();
        pizzaService.createPizza(pizza);
        Assertions.assertEquals(1, pizzaService.getAllPizzas().size());
    }

    @Test
    public void should_return_pizzas() {
        List<Pizza> pizzaList = pizzaService.getAllPizzas();
        Assertions.assertEquals(0, pizzaList.size());
    }

    @Test
    public void should_update_pizza() {
        CreatePizzaDto pizzaCreate = GetPizza();
        pizzaService.createPizza(pizzaCreate);
        List<Pizza> pizzaList = pizzaService.getAllPizzas();
        Pizza pizza = pizzaService.getPizzaById(pizzaList.get(0).getPizzaId()).get();
        UpdatePizzaDto updatePizzaDto = new UpdatePizzaDto(20.0);
        pizzaService.updatePizza(pizza.getPizzaId(), updatePizzaDto);
        Pizza pizzaUpdated = pizzaService.getPizzaById(pizzaList.get(0).getPizzaId()).get();
        Assertions.assertEquals(pizzaUpdated.getPrice(), 20.0);
    }

    public CreatePizzaDto GetPizza(){
        return new CreatePizzaDto("6425b05a66bb0e2c94fbac9f",15.5);
    }

}
