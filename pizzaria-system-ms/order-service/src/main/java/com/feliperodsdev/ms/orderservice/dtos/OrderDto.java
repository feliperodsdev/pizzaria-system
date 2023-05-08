package com.feliperodsdev.ms.orderservice.dtos;

import java.time.LocalDateTime;

public class OrderDto {

    private Long id;
    private LocalDateTime date;
    private Boolean paymentStatus;
    private Double subTotal;

    public OrderDto(Long id, LocalDateTime date, Boolean paymentStatus, Double subTotal) {
        this.id = id;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.subTotal = subTotal;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

    public Double getSubTotal() {
        return subTotal;
    }
}
