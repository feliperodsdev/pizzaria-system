package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.CreatePaymentDto;
import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.services.exceptions.ResourceNotFound;
import com.feliperodsdev.ms.financeservice.services.memory.InMemoryDB;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PaymentServiceTest {

    FinanceService financeService = new FinanceService(new InMemoryDB());

    @Test
    public void should_save_payment(){
        CreatePaymentDto createPaymentDto = getCreatePaymentDto(20.5);
        financeService.createPayment(createPaymentDto);
        assertEquals(1, financeService.getAllPayments().size());
    }

    @Test
    public void should_get_all_payments(){
        financeService.createPayment(getCreatePaymentDto(20.5));
        assertEquals(1, financeService.getAllPayments().size());
    }

    @Test
    public void should_thrown_error_payment_byId(){
        ResourceNotFound resourceNotFound = assertThrows(ResourceNotFound.class, this::findByIdExecError);
        assertEquals("Resource not found.", resourceNotFound.getMessage());
    }

    @Test
    public void should_find_payment_byId(){
        financeService.createPayment(getCreatePaymentDto(22.5));
        Payment payment = financeService.findByIdPayment(financeService.getAllPayments()
                .get(0)
                .getId());
        Assertions.assertEquals(22.5, payment.getValue());
    }

    @Test
    public void should_mark_payment_asCanceled(){
        financeService.createPayment(getCreatePaymentDto(20.5));
        Payment payment = financeService.findByIdPayment(financeService.getAllPayments()
                .get(0)
                .getId());
        financeService.cancelPayment(payment.getId());
        Assertions.assertEquals(PaymentStatus.CANCELED, payment.getStatusPayment());
    }

    public CreatePaymentDto getCreatePaymentDto(Double value){
        return new CreatePaymentDto(value, FinancialTransactionType.REVENUE, Long.valueOf(1));
    }

    public void findByIdExecError(){
        financeService.findByIdPayment(Long.valueOf(1));
    }

}
