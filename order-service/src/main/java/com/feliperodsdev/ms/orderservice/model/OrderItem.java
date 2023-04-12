package com.feliperodsdev.ms.orderservice.model;


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
    private Order order;

    private String pizza_id;
    private BigDecimal price;
    private BigDecimal sub_total;
    private BigDecimal discount;

    public OrderItem(){}

    public static OrderItem create(String pizza_id, Double price, Double discount){
        OrderItem orderItem = new OrderItem();

        if(!orderItem.isValidPrice(price)) throw new EntityValidationException("'price' is invalid.");

        orderItem.price = BigDecimal.valueOf(price);

        if(!orderItem.isValidPizzaId(pizza_id)) throw new EntityValidationException("'pizza_id' is invalid.");

        orderItem.pizza_id = pizza_id;

        if(!orderItem.isValidDiscount(discount)) throw new EntityValidationException("'discount' is invalid.");

        orderItem.discount = BigDecimal.valueOf(discount);

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

    public BigDecimal calcSubTotal(Double discount, Double price){
        return BigDecimal.valueOf((price*discount)/100).setScale(2, RoundingMode.DOWN);
    }

    public String getPizza_id() {
        return pizza_id;
    }
}
