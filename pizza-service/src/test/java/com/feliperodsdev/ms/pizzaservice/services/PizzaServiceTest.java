package com.feliperodsdev.ms.pizzaservice.services;
import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import com.feliperodsdev.ms.pizzaservice.services.memory.InMemoryDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ResouceNotFound;

import java.util.List;

public class PizzaServiceTest {

    PizzaService pizzaService = new PizzaService(new InMemoryDB());

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
    public void should_throw_an_exception(){
        Assertions.assertThrows(ResouceNotFound.class, () -> {
            pizzaService.findPizzaById("invalid id");
        });
    }

    public CreatePizzaDto GetPizza(){
        return new CreatePizzaDto("Calabresa", "One of the best!", 15.5);
    }

}
