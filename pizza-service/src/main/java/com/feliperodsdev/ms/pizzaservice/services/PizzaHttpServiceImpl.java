package com.feliperodsdev.ms.pizzaservice.services;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaOrderDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdateOrderPizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Qualifier("PizzaHttpReq")
public class PizzaHttpServiceImpl implements IPizzaHttpService {

    @Value("${service.order.url}")
    private String urlOrderService;

    public void updatePizzaOrder(String id, UpdatePizzaDto updatePizzaDto){
        UpdateOrderPizzaDto pizzaToUpdateOrder = new UpdateOrderPizzaDto(updatePizzaDto.getPrice());

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdateOrderPizzaDto> request = new HttpEntity<>(pizzaToUpdateOrder, headers);

        restTemplate.exchange(urlOrderService + "pizza/update/" + id, HttpMethod.PUT, request, String.class);
    }

    public void createPizzaOrder(CreatePizzaOrderDto createPizzaDto){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreatePizzaOrderDto> request = new HttpEntity<>(createPizzaDto, headers);

        restTemplate.postForEntity(urlOrderService + "pizza/create", request, String.class);
    }

}
