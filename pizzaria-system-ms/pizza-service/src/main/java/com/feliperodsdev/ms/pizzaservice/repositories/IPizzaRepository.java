package com.feliperodsdev.ms.pizzaservice.repositories;

import com.feliperodsdev.ms.pizzaservice.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface IPizzaRepository {

    void save(Pizza pizza);

    List<Pizza> getAllPizzas();

    Optional<Pizza> findPizzaById(String id);

    void deletePizzaById(String id);

}
