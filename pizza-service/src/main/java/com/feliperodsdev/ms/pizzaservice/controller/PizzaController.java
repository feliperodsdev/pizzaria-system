package com.feliperodsdev.ms.pizzaservice.controller;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import com.feliperodsdev.ms.pizzaservice.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPizza(@RequestBody CreatePizzaDto createPizzaDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"name", "desc", "price"};

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

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllPizzas(){
        HttpResponseDto response = new HttpResponseDto();

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        try {
            List<Pizza> pizzaList = pizzaService.getAllPizzas();
            return response.ok(pizzaList, headers, MediaType.APPLICATION_JSON);
        } catch(Exception e){
            return response.serverError(e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPizzaById(@PathVariable("id") String Id){
        HttpResponseDto response = new HttpResponseDto();

        HttpHeaders headers = new HttpHeaders();
        headers.add("date-time", LocalDateTime.now().toString());

        Pizza pizza = pizzaService.findPizzaById(Id);
        return response.found(pizza, headers, MediaType.APPLICATION_JSON);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePizzaById(@PathVariable("id") String Id, @RequestBody UpdatePizzaDto updatePizzaDto){
        HttpResponseDto response = new HttpResponseDto();

        String[] requiredFields = {"name", "desc", "price"};

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

        pizzaService.updatePizza(Id, updatePizzaDto);

        return response.ok("updated", headers, MediaType.APPLICATION_JSON);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePizza(@PathVariable("id") String id){
        HttpResponseDto response = new HttpResponseDto();
        pizzaService.deletePizzaById(id);
        return response.ok("deleted");
    }

}
