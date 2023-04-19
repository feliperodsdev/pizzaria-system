package com.feliperodsdev.ms.financeservice.model;

import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    @Test
    public void should_create_payment(){
        Payment payment = Payment.create(15.5, Long.getLong("1"));
        Assertions.assertEquals(null, payment.getId());
        Assertions.assertEquals(PaymentStatus.WAITING_PAYMENT, payment.getStatusPayment());
    }

}
