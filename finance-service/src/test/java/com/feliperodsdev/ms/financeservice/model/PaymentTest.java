package com.feliperodsdev.ms.financeservice.model;

import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.model.exceptions.EntityValidationException;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PaymentTest {

    @Test
    public void should_create_payment(){
        Payment payment = Payment.create(15.5, Long.valueOf("1"));
        assertEquals(null, payment.getId());
        assertEquals(PaymentStatus.WAITING_PAYMENT, payment.getStatusPayment());
    }

    @Test
    public void should_thrown_error_entityvalidation(){
        EntityValidationException entityValidationException = assertThrows(EntityValidationException.class,
                this::createPaymentErrorReferenceId);

        assertEquals("'referenceId' is invalid.", entityValidationException.getMessage());
    }

    @Test
    public void should_thrown_error_entityvalidationvalue(){
        EntityValidationException entityValidationException = assertThrows(EntityValidationException.class,
                this::createPaymentErrorValue);

        assertEquals("'value' is invalid.", entityValidationException.getMessage());
    }

    public void createPaymentErrorReferenceId(){
        Payment.create(20.5, Long.valueOf(-4));
    }

    public void createPaymentErrorValue(){
        Payment.create(Double.valueOf(-4), Long.valueOf(5));
    }

}
