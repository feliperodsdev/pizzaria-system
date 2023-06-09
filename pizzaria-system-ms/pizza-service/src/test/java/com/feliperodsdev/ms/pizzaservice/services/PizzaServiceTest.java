package com.feliperodsdev.ms.pizzaservice.services;
import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaOrderDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import com.feliperodsdev.ms.pizzaservice.services.memory.InMemoryDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.feliperodsdev.ms.pizzaservice.services.exceptions.ResouceNotFound;

import java.util.List;

public class PizzaServiceTest {

    private PizzaHttpServiceFake httpServiceMock = new PizzaHttpServiceFake();

    PizzaService pizzaService = new PizzaService(new InMemoryDB(), httpServiceMock);

    @Test
    public void should_create_an_pizza() {
        CreatePizzaDto pizza = GetPizza();
        Pizza createdPizza = pizzaService.createPizza(pizza);
        CreatePizzaOrderDto createPizzaOrderDto = new CreatePizzaOrderDto(createdPizza.getId(), createdPizza.getPrice());
        Assertions.assertEquals(1, httpServiceMock.getCreatePizzaOrderDtoList().size());
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

    @Test
    public void should_update_pizza(){
        Pizza pizza = pizzaService.createPizza(GetPizza());

        UpdatePizzaDto updatedPizzaDto = GetOtherPizza();

        pizzaService.updatePizza(pizza.getId(), updatedPizzaDto);

        Pizza updatedPizza = pizzaService.findPizzaById(pizza.getId());

        Assertions.assertEquals(1, httpServiceMock.getUpdatePizzaDtoList().size());
        Assertions.assertEquals(pizza.getName(), "Peperoni");
        Assertions.assertEquals(pizza.getPrice(), 20.5);
    }

    public CreatePizzaDto GetPizza(){
        return new CreatePizzaDto("Calabresa", "One of the best!", 15.5);
    }

    public UpdatePizzaDto GetOtherPizza(){
        return new UpdatePizzaDto("Peperoni", "One of the best!", 20.5);
    }

}
