package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryPostgres extends JpaRepository<Order, Long> {
}
