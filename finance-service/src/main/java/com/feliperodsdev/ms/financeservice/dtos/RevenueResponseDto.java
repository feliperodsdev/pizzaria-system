package com.feliperodsdev.ms.financeservice.dtos;

import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;

public class RevenueResponseDto {

    private Long id;
    private Double value;
    private PaymentStatus statusPayment;
    private PaymentMethod paymentMethod;
    private Long referenceId;

    public RevenueResponseDto() {
    }

    public RevenueResponseDto(Long id, Double value, PaymentStatus statusPayment, PaymentMethod paymentMethod, Long referenceId) {
        this.id = id;
        this.value = value;
        this.statusPayment = statusPayment;
        this.paymentMethod = paymentMethod;
        this.referenceId = referenceId;
    }

    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public PaymentStatus getStatusPayment() {
        return statusPayment;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Long getReferenceId() {
        return referenceId;
    }

}
