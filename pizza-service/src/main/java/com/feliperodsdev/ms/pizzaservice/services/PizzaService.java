package com.feliperodsdev.ms.pizzaservice.services;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaOrderDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdateOrderPizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import com.feliperodsdev.ms.pizzaservice.repositories.IPizzaRepository;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ResouceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private Environment env;

    private IPizzaRepository pizzaRepository;

    public PizzaService(@Qualifier("PizzaMongoRepository") IPizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public Pizza createPizza(CreatePizzaDto createPizzaDto){
        Pizza pizza = Pizza.Create(createPizzaDto.getName(), createPizzaDto.getDesc(), createPizzaDto.getPrice());

        this.pizzaRepository.save(pizza);

        String orderServiceUrl = env.getProperty("service.order.url");

        String url = orderServiceUrl + "pizza/create";

        CreatePizzaOrderDto pizzaToCreate = new CreatePizzaOrderDto(pizza.getId(), createPizzaDto.getPrice());

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreatePizzaOrderDto> request = new HttpEntity<>(pizzaToCreate, headers);

        restTemplate.postForEntity(url, request, String.class);

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

        String orderServiceUrl = env.getProperty("service.order.url");

        String url = orderServiceUrl + "pizza/update/" + id;

        UpdateOrderPizzaDto pizzaToUpdateOrder = new UpdateOrderPizzaDto(updatePizzaDto.getPrice());

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdateOrderPizzaDto> request = new HttpEntity<>(pizzaToUpdateOrder, headers);

        restTemplate.exchange(url, HttpMethod.PUT, request, String.class);

        this.pizzaRepository.save(pizzaToUpdate);
    }

}
