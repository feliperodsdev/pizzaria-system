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
    public void should_thrown_error_bReferenceId(){
        EntityValidationException entityValidationException = assertThrows(EntityValidationException.class,
                this::createPaymentWithInvalidReferenceId);

        assertEquals("'referenceId' is invalid.", entityValidationException.getMessage());
    }

    @Test
    public void should_thrown_error_bValue(){
        EntityValidationException entityValidationException = assertThrows(EntityValidationException.class,
                this::createPaymentInvalidValue);

        assertEquals("'value' is invalid.", entityValidationException.getMessage());
    }

    @Test
    public void should_mark_asRefund(){
        Payment payment = getPaymentRevenue();
        payment.markAsPaid(PaymentMethod.MONEY);
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
                this::tryToPayPaymentCanceled);

        EntityValidationException entityValidationExceptionPaid = assertThrows(EntityValidationException.class,
                this::tryToPayPaymentPaid);

        EntityValidationException entityValidationExceptionRefund = assertThrows(EntityValidationException.class,
                this::tryToPayPaymentRefunded);

        Assertions.assertEquals("Payment is not waiting payment.", entityValidationExceptionRefund.getMessage());
        Assertions.assertEquals("Payment is not waiting payment.", entityValidationExceptionPaid.getMessage());
        Assertions.assertEquals("Payment is not waiting payment.", entityValidationExceptionCanceled.getMessage());
    }

    @Test
    public void should_not_be_able_to_refund_waitingpayment(){
        EntityValidationException entityValidationException = assertThrows(EntityValidationException.class,
                this::tryMarkAsRefundPaymentNotPaid);

        Assertions.assertEquals("This Payment is not paid yet.", entityValidationException.getMessage());
    }

    @Test
    public void should_not_be_able_to_refund_expense(){
        EntityValidationException entityValidationException = assertThrows(EntityValidationException.class,
                this::tryToRefundAnExpense);

        Assertions.assertEquals("Not able to refund an expense.",
                entityValidationException.getMessage());
    }

    public void tryMarkAsRefundPaymentNotPaid(){
        Payment payment = Payment.create(20.5, Long.valueOf(1), FinancialTransactionType.REVENUE);
        payment.markAsRefund();
    }

    public void createPaymentWithInvalidReferenceId(){
        Payment.create(20.5, Long.valueOf(-4), FinancialTransactionType.REVENUE);
    }

    public void createPaymentInvalidValue(){
        Payment.create(Double.valueOf(-4), Long.valueOf(5), FinancialTransactionType.REVENUE);
    }

    public void tryToPayPaymentCanceled(){
        Payment payment = getPaymentRevenue();
        payment.markAsCanceled();
        payment.markAsPaid(PaymentMethod.MONEY);
    }

    public void tryToPayPaymentPaid(){
        Payment payment = getPaymentRevenue();
        payment.markAsPaid(PaymentMethod.MONEY);
        payment.markAsPaid(PaymentMethod.MONEY);
    }

    public void tryToPayPaymentRefunded(){
        Payment payment = getPaymentRevenue();
        payment.markAsPaid(PaymentMethod.MONEY);
        payment.markAsRefund();
        payment.markAsPaid(PaymentMethod.MONEY);
    }

    public void tryToRefundAnExpense(){
        Payment payment = getPaymentExpense();
        payment.markAsPaid(PaymentMethod.MONEY);
        payment.markAsRefund();
    }

    public Payment getPaymentRevenue(){
        return Payment.create(20.5, Long.valueOf(5), FinancialTransactionType.REVENUE);
    }

    public Payment getPaymentExpense(){
        return Payment.create(20.5, Long.valueOf(5), FinancialTransactionType.EXPENSE);
    }

}
