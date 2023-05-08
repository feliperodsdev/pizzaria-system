package com.feliperodsdev.ms.orderservice.services;

import com.feliperodsdev.ms.orderservice.dtos.CreateOrderDto;
import com.feliperodsdev.ms.orderservice.dtos.OrderDto;
import com.feliperodsdev.ms.orderservice.dtos.OrderItemDto;
import com.feliperodsdev.ms.orderservice.model.Order;
import com.feliperodsdev.ms.orderservice.model.OrderItem;
import com.feliperodsdev.ms.orderservice.model.Pizza;
import com.feliperodsdev.ms.orderservice.repositories.IOrderRepository;
import com.feliperodsdev.ms.orderservice.repositories.IPizzaRepository;
import com.feliperodsdev.ms.orderservice.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private IOrderRepository orderRepository;

    private IPizzaRepository pizzaRepository;

    public OrderService(@Qualifier("PostgresOrderRepository") IOrderRepository orderRepository,
                        @Qualifier("PostgresPizzaRepository") IPizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    public Order createOrder(CreateOrderDto createOrderDto) {
        Order order = Order.create(mapToListOrderItem(createOrderDto.getOrderItemDtoList()));
        orderRepository.save(order);
        return order;
    }

    public List<OrderDto> getOrders(){
        List<OrderDto> orderDtoList = new ArrayList<>();

        List<Order> listOrder = orderRepository.findAll();

        for(Order order: listOrder){
            Double sub_total = 0.0;
            for(OrderItem orderItem: order.getOrderItemList()){
                sub_total+=orderItem.getSubTotal();
            }
            orderDtoList.add(new OrderDto(order.getId(), order.getDate(), order.getPaymentStatus(), sub_total));
        }

        return orderDtoList;
    }

    public List<OrderItem> mapToListOrderItem(List<OrderItemDto> list){
        List<OrderItem> orderItemList = new ArrayList<>();

        for(OrderItemDto orderItemDto: list){
            Pizza pizza = getPizza(orderItemDto.getPizzaId());

            OrderItem orderItem = OrderItem.create(pizza.getPizzaId(),
                    pizza.getPrice(),
                    orderItemDto.getDiscount());

            orderItemList.add(orderItem);
        }
        return orderItemList;
    }

    public Pizza getPizza(String id){
        return pizzaRepository.findPizzaById(id).orElseThrow(() -> new ResourceNotFound("" +
                "Pizza not found."));
    }

}
