package com.feliperodsdev.ms.orderservice.controllers;

import com.feliperodsdev.ms.orderservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.orderservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.orderservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.orderservice.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @RequestMapping("/create")
    public ResponseEntity<Object> createPizza(@RequestBody CreatePizzaDto createPizzaDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"pizzaId", "price"};

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

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePizza(@RequestParam("id") String id, @RequestBody UpdatePizzaDto updatePizzaDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"price"};

        for (String field : requiredFields) {
            try {
                Field declaredField = updatePizzaDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(updatePizzaDto) == null) {
                    return response.badRequest("Missing Param: " + field);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        pizzaService.updatePizza(id, updatePizzaDto);

        return response.ok("updated");

    }

}
