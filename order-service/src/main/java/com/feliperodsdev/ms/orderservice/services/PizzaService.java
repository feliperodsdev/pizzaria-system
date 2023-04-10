package com.feliperodsdev.ms.orderservice.services;

import com.feliperodsdev.ms.orderservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.orderservice.model.Pizza;
import com.feliperodsdev.ms.orderservice.repositories.IPizzaRepository;
import com.feliperodsdev.ms.orderservice.services.exceptions.ResourceAlreadyExists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private IPizzaRepository pizzaRepository;

    public PizzaService(@Qualifier("PostgresPizzaRepository") IPizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public void createPizza(CreatePizzaDto createPizzaDto){
        Optional<Pizza> pizzaOptional = pizzaRepository.findPizzaById(createPizzaDto.getPizza_id());

        if(!pizzaOptional.isEmpty()) throw new ResourceAlreadyExists("This 'id' already exists.");

        Pizza pizza = Pizza.Create(createPizzaDto.getPizza_id(), createPizzaDto.getPrice());
        pizzaRepository.savePizza(pizza);
    }

    public List<Pizza> getAllPizzas(){
        return pizzaRepository.getPizzas();
    }

}
