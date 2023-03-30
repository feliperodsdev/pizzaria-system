package com.feliperodsdev.ms.pizzaservice.services;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import com.feliperodsdev.ms.pizzaservice.repositories.IPizzaRepository;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ResouceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private IPizzaRepository pizzaRepository;

    public PizzaService(@Qualifier("PizzaMongoRepository") IPizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public void CreatePizza(CreatePizzaDto createPizzaDto){
        Pizza pizza = Pizza.Create(createPizzaDto.getName(), createPizzaDto.getDesc(), createPizzaDto.getPrice());
        this.pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizzas(){
        return this.pizzaRepository.getAllPizzas();
    }

    public Pizza findPizzaById(String id){
        Optional<Pizza> pizza = this.pizzaRepository.findPizzaById(id);
        return pizza.orElseThrow(() -> new ResouceNotFound(id));
    }

}
