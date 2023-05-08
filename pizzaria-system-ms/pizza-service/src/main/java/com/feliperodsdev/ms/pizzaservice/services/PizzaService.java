package com.feliperodsdev.ms.pizzaservice.services;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaOrderDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;
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

    private IPizzaHttpService pizzaHttpService;

    public PizzaService(@Qualifier("PizzaMongoRepository") IPizzaRepository pizzaRepository,
                        @Qualifier("PizzaHttpReq") IPizzaHttpService pizzaHttpService) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaHttpService = pizzaHttpService;
    }

    public Pizza createPizza(CreatePizzaDto createPizzaDto){
        Pizza pizza = Pizza.Create(createPizzaDto.getName(), createPizzaDto.getDesc(), createPizzaDto.getPrice());

        this.pizzaRepository.save(pizza);

        CreatePizzaOrderDto pizzaToCreate = new CreatePizzaOrderDto(pizza.getId(), createPizzaDto.getPrice());
        pizzaHttpService.createPizzaOrder(pizzaToCreate);

        return pizza;
    }

    public List<Pizza> getAllPizzas(){
        return this.pizzaRepository.getAllPizzas();
    }

    public Pizza findPizzaById(String id){
        Optional<Pizza> pizza = this.pizzaRepository.findPizzaById(id);
        return pizza.orElseThrow(() -> new ResouceNotFound(id));
    }

    public void updatePizza(String id, UpdatePizzaDto updatePizzaDto){
        Pizza pizzaToUpdate = findPizzaById(id);

        pizzaToUpdate.updateName(updatePizzaDto.getName());
        pizzaToUpdate.updatePrice(updatePizzaDto.getPrice());
        pizzaToUpdate.updateDesc(updatePizzaDto.getDesc());

        pizzaHttpService.updatePizzaOrder(id, updatePizzaDto);

        this.pizzaRepository.save(pizzaToUpdate);
    }

    public void deletePizzaById(String id){
        findPizzaById(id);
        pizzaHttpService.deletePizzaOrder(id);
        this.pizzaRepository.deletePizzaById(id);
    }

}
