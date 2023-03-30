package com.feliperodsdev.ms.pizzaservice.repositories;

import com.feliperodsdev.ms.pizzaservice.model.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PizzaMongoRepository extends MongoRepository<Pizza, String> {
}
