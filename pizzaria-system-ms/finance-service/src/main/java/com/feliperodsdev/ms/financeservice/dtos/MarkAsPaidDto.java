package com.feliperodsdev.ms.financeservice.dtos;

import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;

public class MarkAsPaidDto {

    private PaymentMethod paymentMethod;

    public MarkAsPaidDto() {
    }

    public MarkAsPaidDto(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

}
