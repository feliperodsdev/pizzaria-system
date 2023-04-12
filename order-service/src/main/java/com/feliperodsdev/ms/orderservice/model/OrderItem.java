package com.feliperodsdev.ms.orderservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String pizza_id;
    private Double price;
    private Double sub_total;
    private Double discount;

    public OrderItem(){}

    public static OrderItem create(String pizza_id, Double price, Double discount){
        OrderItem orderItem = new OrderItem();

        if(!orderItem.isValidPrice(price)) throw new EntityValidationException("'price' is invalid.");

        orderItem.price = price;

        if(!orderItem.isValidPizzaId(pizza_id)) throw new EntityValidationException("'pizza_id' is invalid.");

        orderItem.pizza_id = pizza_id;

        if(!orderItem.isValidDiscount(discount)) throw new EntityValidationException("'discount' is invalid.");

        orderItem.discount = discount;

        orderItem.sub_total = orderItem.calcSubTotal(discount, price);

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

    public String getPizza_id() {
        return pizza_id;
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

    public Double getSub_total() {
        return sub_total;
    }

    @JsonIgnore
    public Double getDiscount() {
        return discount;
    }
}
