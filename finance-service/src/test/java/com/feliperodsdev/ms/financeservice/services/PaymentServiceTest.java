package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.CreatePaymentDto;
import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;
import com.feliperodsdev.ms.financeservice.services.memory.InMemoryDB;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentServiceTest {

    FinanceService financeService = new FinanceService(new InMemoryDB());

    @Test
    public void should_save_payment(){
        CreatePaymentDto createPaymentDto = getCreatePaymentDto();
        financeService.createPayment(createPaymentDto);
        Assertions.assertEquals(1, financeService.getAllPayments().size());
    }

    @Test
    public void should_get_all_payments(){
        financeService.createPayment(getCreatePaymentDto());
        Assertions.assertEquals(1, financeService.getAllPayments().size());
    }

    public CreatePaymentDto getCreatePaymentDto(){
        return new CreatePaymentDto(15.5, FinancialTransactionType.REVENUE, Long.valueOf(1));
    }

}
