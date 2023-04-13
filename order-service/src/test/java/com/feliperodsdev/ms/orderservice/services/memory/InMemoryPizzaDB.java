package com.feliperodsdev.ms.orderservice.services.memory;

import com.feliperodsdev.ms.orderservice.model.Pizza;
import com.feliperodsdev.ms.orderservice.repositories.IPizzaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryPizzaDB implements IPizzaRepository {

    private List<Pizza> pizzaList = new ArrayList<>();

    @Override
    public void savePizza(Pizza pizza) {
        this.pizzaList.add(pizza);
    }

    @Override
    public List<Pizza> getPizzas() {
        return this.pizzaList;
    }

    @Override
    public Optional<Pizza> findPizzaById(String id) {
        for (Pizza pizza : pizzaList) {
            if (pizza.getPizzaId().equals(id)) {
                return Optional.of(pizza);
            }
        }
        return Optional.empty();
    }

}
