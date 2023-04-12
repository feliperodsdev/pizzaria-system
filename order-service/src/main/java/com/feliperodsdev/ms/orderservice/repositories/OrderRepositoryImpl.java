package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("PostgresOrderRepository")
public class OrderRepositoryImpl implements IOrderRepository {

    @Autowired
    OrderRepositoryPostgres orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
