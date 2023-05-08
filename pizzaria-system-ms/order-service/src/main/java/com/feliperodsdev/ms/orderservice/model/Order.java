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
    private Boolean paymentStatus;
    private LocalDateTime date;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

    public Order(){}

    public Long getId() {
        return id;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public static Order create(List<OrderItem> orderItemList){
        Order order = new Order();

        if(!order.isValidListOrderItem(orderItemList)) throw new EntityValidationException("Order cannot be placed without product(s).");

        for (OrderItem orderItem: orderItemList){
            orderItem.setOrder(order);
        }

        order.orderItemList = orderItemList;
        order.paymentStatus = false;
        order.date = LocalDateTime.now();

        return order;
    }

    public boolean isValidListOrderItem(List<OrderItem> orderItemList){
        return orderItemList.size() > 0;
    }

    public void updatePaymentStatus(){
        if(this.paymentStatus) this.paymentStatus = false;
        else this.paymentStatus = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
