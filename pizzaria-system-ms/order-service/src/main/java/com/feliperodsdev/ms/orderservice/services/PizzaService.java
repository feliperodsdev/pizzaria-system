package com.feliperodsdev.ms.orderservice.services;

import com.feliperodsdev.ms.orderservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.orderservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.orderservice.model.Pizza;
import com.feliperodsdev.ms.orderservice.repositories.IPizzaRepository;
import com.feliperodsdev.ms.orderservice.services.exceptions.ResourceAlreadyExists;
import com.feliperodsdev.ms.orderservice.services.exceptions.ResourceNotFound;
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
        Optional<Pizza> pizzaOptional = pizzaRepository.findPizzaById(createPizzaDto.getPizzaId());

        if(!pizzaOptional.isEmpty()) throw new ResourceAlreadyExists("This 'id' already exists.");

        Pizza pizza = Pizza.Create(createPizzaDto.getPizzaId(), createPizzaDto.getPrice());
        pizzaRepository.savePizza(pizza);
    }

    public Pizza getPizzaById(String id){
        return pizzaRepository.findPizzaById(id).orElseThrow(()
                -> new ResourceNotFound("Pizza not found, id: " + id));
    }

    public List<Pizza> getAllPizzas(){
        return pizzaRepository.getPizzas();
    }

    public void updatePizza(String id, UpdatePizzaDto updatePizzaDto){
        Pizza pizzaToUpdate = getPizzaById(id);

        pizzaToUpdate.updatePrice(updatePizzaDto.getPrice());
        pizzaRepository.savePizza(pizzaToUpdate);
    }

    public void deletePizzaById(String id){
        getPizzaById(id);
        pizzaRepository.deletePizzaById(id);
    }

}
