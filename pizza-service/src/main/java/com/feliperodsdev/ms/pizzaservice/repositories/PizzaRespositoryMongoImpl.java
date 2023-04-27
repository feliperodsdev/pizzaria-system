package com.feliperodsdev.ms.pizzaservice.repositories;

import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("PizzaMongoRepository")
public class PizzaRespositoryMongoImpl implements IPizzaRepository{

    @Autowired
    PizzaMongoRepository pizzaMongoRepository;

    @Override
    public void save(Pizza pizza) {
        this.pizzaMongoRepository.save(pizza);
    }

    @Override
    public List<Pizza> getAllPizzas() {
        return this.pizzaMongoRepository.findAll();
    }

    @Override
    public Optional<Pizza> findPizzaById(String id) {
        return this.pizzaMongoRepository.findById(id);
    }

    @Override
    public void deletePizzaById(String id) {
        this.pizzaMongoRepository.deleteById(id);
    }

}
