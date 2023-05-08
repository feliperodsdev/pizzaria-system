package com.feliperodsdev.ms.pizzaservice.services;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaOrderDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;

public interface IPizzaHttpService {

    void updatePizzaOrder(String id, UpdatePizzaDto updatePizzaDto);
    void createPizzaOrder(CreatePizzaOrderDto createPizzaDto);
    void deletePizzaOrder(String id);

}
