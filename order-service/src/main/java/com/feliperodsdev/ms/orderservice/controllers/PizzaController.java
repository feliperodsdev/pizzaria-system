package com.feliperodsdev.ms.orderservice.controllers;

import com.feliperodsdev.ms.orderservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.orderservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.orderservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.orderservice.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @RequestMapping("/create")
    public ResponseEntity<Object> createPizza(@RequestBody CreatePizzaDto createPizzaDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"pizzaId", "price"};

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        for (String field : requiredFields) {
            try {
                Field declaredField = createPizzaDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(createPizzaDto) == null) {
                    return response.badRequest("Missing Param: " + field, headers, MediaType.APPLICATION_JSON);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        pizzaService.createPizza(createPizzaDto);

        return response.created("created", headers, MediaType.APPLICATION_JSON);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updatePizza(@PathVariable("id") String id, @RequestBody UpdatePizzaDto updatePizzaDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"price"};

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        for (String field : requiredFields) {
            try {
                Field declaredField = updatePizzaDto.getClass().getDeclaredField(field);
                declaredField.setAccessible(true);
                if (declaredField.get(updatePizzaDto) == null) {
                    return response.badRequest("Missing Param: " + field, headers, MediaType.APPLICATION_JSON);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return response.serverError(e.getMessage());
            }
        }

        pizzaService.updatePizza(id, updatePizzaDto);

        return response.ok("updated", headers, MediaType.APPLICATION_JSON);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePizza(@PathVariable("id") String id){
        HttpResponseDto response = new HttpResponseDto();
        pizzaService.deletePizzaById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        return response.ok("deleted", headers, MediaType.APPLICATION_JSON);
    }

}
