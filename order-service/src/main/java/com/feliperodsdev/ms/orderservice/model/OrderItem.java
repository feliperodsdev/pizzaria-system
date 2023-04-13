package com.feliperodsdev.ms.orderservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;

import javax.persistence.*;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String pizzaId;
    private Double price;
    private Double subTotal;
    private Double discount;

    public OrderItem(){}

    public static OrderItem create(String pizzaId, Double price, Double discount){
        OrderItem orderItem = new OrderItem();

        if(!orderItem.isValidPrice(price)) throw new EntityValidationException("'price' is invalid.");

        orderItem.price = price;

        if(!orderItem.isValidPizzaId(pizzaId)) throw new EntityValidationException("'pizza_id' is invalid.");

        orderItem.pizzaId = pizzaId;

        if(!orderItem.isValidDiscount(discount)) throw new EntityValidationException("'discount' is invalid.");

        orderItem.discount = discount;

        orderItem.subTotal = orderItem.calcSubTotal(discount, price);

        return orderItem;
    }

    public Boolean isValidDiscount(Double discount){
        return discount >= 0 && discount <= 100;
    }

    public Boolean isValidPizzaId (String id){
        id = id.trim();

        return !id.isEmpty();
    }

    public Boolean isValidPrice(Double price){
        return price >= 0;
    }

    public Double calcSubTotal(Double discount, Double price){
        return price - ((price*discount)/100);
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public Double getPrice() {
        return price;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    @JsonIgnore
    public Double getDiscount() {
        return discount;
    }

}
