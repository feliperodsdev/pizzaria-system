package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Order;

public interface IOrderRepository {

    Order save(Order order);

}
