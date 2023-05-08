package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Pizza;

import java.util.List;
import java.util.Optional;

public interface IPizzaRepository {

     void savePizza(Pizza pizza);
     List<Pizza> getPizzas();
     Optional<Pizza> findPizzaById(String id);
     void deletePizzaById(String id);

}
