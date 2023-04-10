package com.feliperodsdev.ms.orderservice.controllers;

import com.feliperodsdev.ms.orderservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.orderservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.orderservice.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @RequestMapping("/create")
    public ResponseEntity<Object> createPizza(@RequestBody CreatePizzaDto createPizzaDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"pizza_id", "price"};

        for (String field : requiredFields) {
            try {
                Field declaredField = createPizzaDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(createPizzaDto) == null) {
                    return response.badRequest("Missing Param: " + field);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        pizzaService.createPizza(createPizzaDto);

        return response.created("created");

    }

}
