package com.feliperodsdev.ms.orderservice.dtos;

import java.time.LocalDateTime;

public class OrderDto {

    private Long id;
    private LocalDateTime date;
    private Boolean payment_status;
    private Double sub_total;

    public OrderDto(Long id, LocalDateTime date, Boolean payment_status, Double sub_total) {
        this.id = id;
        this.date = date;
        this.payment_status = payment_status;
        this.sub_total = sub_total;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getPayment_status() {
        return payment_status;
    }

    public Double getSub_total() {
        return sub_total;
    }
}
