package com.feliperodsdev.ms.pizzaservice.services.memory;

import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import com.feliperodsdev.ms.pizzaservice.repositories.IPizzaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDB implements IPizzaRepository {

    private List<Pizza> pizzaList = new ArrayList<>();

    public void save(Pizza pizza) {
        this.pizzaList.add(pizza);
    }

    public List<Pizza> getAllPizzas() {
        return this.pizzaList;
    }

    @Override
    public Optional<Pizza> findPizzaById(String id) {
        for (Pizza person : pizzaList) {
            if (person.getId().equals(id)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }
}
