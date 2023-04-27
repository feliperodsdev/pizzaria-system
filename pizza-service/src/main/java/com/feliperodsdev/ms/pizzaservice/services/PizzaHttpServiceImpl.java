package com.feliperodsdev.ms.pizzaservice.services;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaOrderDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdateOrderPizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.services.exceptions.ServiceNotWorking;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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

        ResponseEntity<Object> response = restTemplate.exchange(urlOrderService + "pizza/update/" + id,
                HttpMethod.PUT,
                request, Object.class);

        if(!response.getStatusCode().is2xxSuccessful())
            throw new ServiceNotWorking();
    }

    public void createPizzaOrder(CreatePizzaOrderDto createPizzaDto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreatePizzaOrderDto> request = new HttpEntity<>(createPizzaDto, headers);

        ResponseEntity<Object> response = restTemplate.exchange(urlOrderService +
                            "pizza/create",
                    HttpMethod.POST, request, Object.class);

        if(!response.getStatusCode().is2xxSuccessful())
            throw new ServiceNotWorking();

    }

    public void deletePizzaOrder(String id){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        ResponseEntity<Object> response = restTemplate.exchange(urlOrderService
                + "pizza/delete/" + id, HttpMethod.DELETE, null, Object.class);

        if(!response.getStatusCode().is2xxSuccessful())
            throw new ServiceNotWorking();

    }

}
