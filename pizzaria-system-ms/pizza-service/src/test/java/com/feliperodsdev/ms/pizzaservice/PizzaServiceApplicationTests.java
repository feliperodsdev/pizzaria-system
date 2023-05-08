package com.feliperodsdev.ms.pizzaservice;

import com.feliperodsdev.ms.pizzaservice.dtos.CreatePizzaDto;
import com.feliperodsdev.ms.pizzaservice.repositories.PizzaRespositoryMongoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class PizzaServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PizzaRespositoryMongoImpl pizzaRespositoryMongo;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void should_create_an_pizza() throws Exception {
		CreatePizzaDto createPizzaDto = getPizzaDto();

		String pizzaRequestString = objectMapper.writeValueAsString(createPizzaDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(pizzaRequestString))
				.andExpect(status().is(201));
		Assertions.assertEquals(1, pizzaRespositoryMongo.getAllPizzas().size());
	}

	@Test
	void should_return_pizzas() throws Exception {
		CreatePizzaDto createPizzaDto = getPizzaDto();

		String pizzaRequestString = objectMapper.writeValueAsString(createPizzaDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(pizzaRequestString));

		Assertions.assertEquals(1, pizzaRespositoryMongo.getAllPizzas().size());
	}

	private CreatePizzaDto getPizzaDto(){
		return new CreatePizzaDto("Calabresa", "The GOAT", 151.7);
	}

}
