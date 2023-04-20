package com.feliperodsdev.ms.financeservice.model;

import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;
import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.model.exceptions.EntityValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PaymentTest {

    @Test
    public void should_create_payment(){
        Payment payment = getPaymentRevenue();
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

    @Test
    public void should_mark_asrefund(){
        Payment payment = getPaymentRevenue();
        Assertions.assertEquals(PaymentStatus.WAITING_PAYMENT, payment.getStatusPayment());
        payment.markAsRefund();
        Assertions.assertEquals(PaymentStatus.REFUND, payment.getStatusPayment());
    }

    @Test
    public void should_mark_asPaid(){
        Payment payment = getPaymentRevenue();
        Assertions.assertEquals(payment.getStatusPayment(), PaymentStatus.WAITING_PAYMENT);
        payment.markAsPaid(PaymentMethod.MONEY);
        Assertions.assertEquals(payment.getStatusPayment(), PaymentStatus.PAID);
    }

    @Test
    public void should_not_be_able_tomarkAsPaid(){
        EntityValidationException entityValidationExceptionCanceled = assertThrows(EntityValidationException.class,
                this::tryToPayErrorCanceled);

        EntityValidationException entityValidationExceptionPaid = assertThrows(EntityValidationException.class,
                this::tryToPayErrorPaid);

        EntityValidationException entityValidationExceptionRefund = assertThrows(EntityValidationException.class,
                this::tryToPayErrorRefund);

        Assertions.assertEquals("Payment is not waiting payment.", entityValidationExceptionRefund.getMessage());
        Assertions.assertEquals("Payment is not waiting payment.", entityValidationExceptionPaid.getMessage());
        Assertions.assertEquals("Payment is not waiting payment.", entityValidationExceptionCanceled.getMessage());
    }

    public void createPaymentErrorReferenceId(){
        Payment.create(20.5, Long.valueOf(-4), FinancialTransactionType.REVENUE);
    }

    public void createPaymentErrorValue(){
        Payment.create(Double.valueOf(-4), Long.valueOf(5), FinancialTransactionType.REVENUE);
    }

    public void tryToPayErrorCanceled(){
        Payment payment = getPaymentRevenue();
        payment.markAsCanceled();
        payment.markAsPaid(PaymentMethod.MONEY);
    }

    public void tryToPayErrorPaid(){
        Payment payment = getPaymentRevenue();
        payment.markAsPaid(PaymentMethod.MONEY);
        payment.markAsPaid(PaymentMethod.MONEY);
    }

    public void tryToPayErrorRefund(){
        Payment payment = getPaymentRevenue();
        payment.markAsRefund();
        payment.markAsPaid(PaymentMethod.MONEY);
    }

    public Payment getPaymentRevenue(){
        return Payment.create(20.5, Long.valueOf(5), FinancialTransactionType.REVENUE);
    }

}
