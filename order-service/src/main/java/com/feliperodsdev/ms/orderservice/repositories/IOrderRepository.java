package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Order;

import java.util.List;

public interface IOrderRepository {

    Order save(Order order);
    List<Order> findAll();

}
