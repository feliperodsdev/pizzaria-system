package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.CreatePaymentDto;
import com.feliperodsdev.ms.financeservice.dtos.MarkAsPaidDto;
import com.feliperodsdev.ms.financeservice.dtos.ReportQuantityDto;
import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;
import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.services.exceptions.ResourceNotFound;
import com.feliperodsdev.ms.financeservice.services.memory.InMemoryDB;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PaymentServiceTest {

    @Test
    public void should_save_payment(){
        FinanceService financeService = new FinanceService(new InMemoryDB());
        CreatePaymentDto createPaymentDto = getCreatePaymentDto(20.5);
        financeService.createPayment(createPaymentDto);
        assertEquals(1, financeService.getAllPayments().size());
    }

    @Test
    public void should_get_all_payments(){
        FinanceService financeService = new FinanceService(new InMemoryDB());
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
        FinanceService financeService = new FinanceService(new InMemoryDB());
        financeService.createPayment(getCreatePaymentDto(22.5));
        Payment payment = financeService.findByIdPayment(financeService.getAllPayments()
                .get(0)
                .getId());
        Assertions.assertEquals(22.5, payment.getValue());
    }

    @Test
    public void should_mark_payment_asCanceled(){
        FinanceService financeService = new FinanceService(new InMemoryDB());
        financeService.createPayment(getCreatePaymentDto(20.5));
        Payment payment = financeService.findByIdPayment(financeService.getAllPayments()
                .get(0)
                .getId());
        financeService.cancelPayment(payment.getId());
        Assertions.assertEquals(PaymentStatus.CANCELED, payment.getStatusPayment());
    }

    @Test
    public void should_mark_payment_asRefund(){
        FinanceService financeService = new FinanceService(new InMemoryDB());
        financeService.createPayment(getCreatePaymentDto(20.5));
        Payment payment = financeService.findByIdPayment(financeService.getAllPayments()
                .get(0)
                .getId());
        financeService.markAsPaid(payment.getId(), new MarkAsPaidDto(PaymentMethod.MONEY));
        financeService.markAsRefund(payment.getId());
        Assertions.assertEquals(PaymentStatus.REFUND, payment.getStatusPayment());
    }

    @Test
    public void should_mark_payment_asPaid(){
        FinanceService financeService = new FinanceService(new InMemoryDB());
        financeService.createPayment(getCreatePaymentDto(20.5));
        Payment payment = financeService.findByIdPayment(financeService.getAllPayments()
                .get(0)
                .getId());
        financeService.markAsPaid(payment.getId(), new MarkAsPaidDto(PaymentMethod.MONEY));
        financeService.markAsRefund(payment.getId());
        Assertions.assertEquals(PaymentStatus.REFUND, payment.getStatusPayment());
    }

    @Test
    public void should_return_report_correct(){
        FinanceService financeService = new FinanceService(new InMemoryDB());
        //creating at least one of each payment type
        //waiting payment
        financeService.createPayment(getCreatePaymentDto(20.5));
        //paid payment
        financeService.createPayment(getCreatePaymentDto(20.5));
        Payment payment = financeService.findByIdPayment(financeService.getAllPayments()
                .get(0)
                .getId());
        financeService.markAsPaid(payment.getId(), new MarkAsPaidDto(PaymentMethod.MONEY));
        //refund payment
        financeService.createPayment(getCreatePaymentDto(20.5));
        Payment payment2 = financeService.findByIdPayment(financeService.getAllPayments()
                .get(1)
                .getId());
        financeService.markAsPaid(payment2.getId(), new MarkAsPaidDto(PaymentMethod.MONEY));
        financeService.markAsRefund(payment2.getId());
        //Assertions
        ReportQuantityDto reportQuantityDto = financeService.getReportQuantity(FinancialTransactionType.REVENUE);
        Assertions.assertEquals(0, reportQuantityDto.getQuantityCanceled());
        Assertions.assertEquals(reportQuantityDto.getQuantityWaitingPayment(), 1);

    }

    public CreatePaymentDto getCreatePaymentDto(Double value){
        return new CreatePaymentDto(value, FinancialTransactionType.REVENUE, Long.valueOf(1));
    }

    public void findByIdExecError(){
        FinanceService financeService = new FinanceService(new InMemoryDB());
        financeService.findByIdPayment(Long.valueOf(1));
    }

}
