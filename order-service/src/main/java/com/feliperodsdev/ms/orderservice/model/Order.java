package com.feliperodsdev.ms.orderservice.model;

import com.feliperodsdev.ms.orderservice.model.exceptions.EntityValidationException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean payment_status;
    private LocalDateTime date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> order_item_list;

    public Order(){}

    public static Order create(List<OrderItem> order_item_list){
        Order order = new Order();

        if(!order.isValidListOrderItem(order_item_list)) throw new EntityValidationException("Order cannot be placed without product.");

        order.order_item_list = order_item_list;
        order.payment_status = false;
        order.date = LocalDateTime.now();

        return order;
    }

    public boolean isValidListOrderItem(List<OrderItem> order_item_list){
        return order_item_list.size() > 0;
    }

    public void updatePaymentStatus(){
        if(this.payment_status) this.payment_status = false;
        else this.payment_status = true;
    }

}
