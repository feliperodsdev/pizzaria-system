package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("PostgresPizzaRepository")
public class PizzaRepositoryImpl implements IPizzaRepository {

    @Autowired
    PizzaRepositoryPostgres pizzaRepositoryPostgres;

    @Override
    public void savePizza(Pizza pizza) {
        this.pizzaRepositoryPostgres.save(pizza);
    }

    @Override
    public List<Pizza> getPizzas() {
        return this.pizzaRepositoryPostgres.findAll();
    }

    @Override
    public Optional<Pizza> findPizzaById(String id) {
        return this.pizzaRepositoryPostgres.findById(id);
    }

    @Override
    public void deletePizzaById(String id) {
        pizzaRepositoryPostgres.deleteById(id);
    }

}
