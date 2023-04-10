package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Pizza;

import java.util.List;

public interface IPizzaRepository {

     void savePizza(Pizza pizza);
     List<Pizza> getPizzas();
}
