package com.feliperodsdev.ms.orderservice.services.memory;

import com.feliperodsdev.ms.orderservice.model.Order;
import com.feliperodsdev.ms.orderservice.repositories.IOrderRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderDB implements IOrderRepository {

    private List<Order> orderList = new ArrayList<>();

    @Override
    public Order save(Order order) {
        this.orderList.add(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return orderList;
    }

}
