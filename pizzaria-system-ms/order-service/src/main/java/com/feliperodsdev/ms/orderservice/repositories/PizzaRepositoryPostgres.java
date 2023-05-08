package com.feliperodsdev.ms.orderservice.repositories;

import com.feliperodsdev.ms.orderservice.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepositoryPostgres extends JpaRepository<Pizza, String> {
}
