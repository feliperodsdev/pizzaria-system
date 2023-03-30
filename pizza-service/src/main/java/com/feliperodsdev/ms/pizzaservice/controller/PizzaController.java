package com.feliperodsdev.ms.pizzaservice.controller;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.dtos.HttpResponseDto;
import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import com.feliperodsdev.ms.pizzaservice.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
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

        pizzaService.CreatePizza(createPizzaDto);

        return response.created("created");
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllPizzas(){
        HttpResponseDto response = new HttpResponseDto();

        try {
            List<Pizza> pizzaList = pizzaService.getAllPizzas();
            return response.ok(pizzaList);
        } catch(Exception e){
            return response.serverError(e);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPizzaById(@PathVariable("id") String Id){
        HttpResponseDto response = new HttpResponseDto();
        Pizza pizza = pizzaService.findPizzaById(Id);
        return response.found(pizza);
    }

}
