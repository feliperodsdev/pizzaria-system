package com.feliperodsdev.ms.pizzaservice.services;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaOrderDto;
import com.feliperodsdev.ms.pizzaservice.dtos.UpdatePizzaDto;

import java.util.ArrayList;
import java.util.List;

public class PizzaHttpServiceFake implements IPizzaHttpService {

    private List<CreatePizzaOrderDto> createPizzaOrderDtoList = new ArrayList<>();
    private List<UpdatePizzaDto> updatePizzaDtoList = new ArrayList<>();

    @Override
    public void updatePizzaOrder(String id, UpdatePizzaDto updatePizzaDto) {
        updatePizzaDtoList.add(updatePizzaDto);
    }

    @Override
    public void createPizzaOrder(CreatePizzaOrderDto createPizzaDto) {
        createPizzaOrderDtoList.add(createPizzaDto);
    }

    @Override
    public void deletePizzaOrder(String id) {
        createPizzaOrderDtoList.remove(0);
    }

    public List<CreatePizzaOrderDto> getCreatePizzaOrderDtoList(){
        return this.createPizzaOrderDtoList;
    }

    public List<UpdatePizzaDto> getUpdatePizzaDtoList(){
        return this.updatePizzaDtoList;
    }

}
