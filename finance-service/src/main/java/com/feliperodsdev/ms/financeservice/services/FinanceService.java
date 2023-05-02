package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.*;
import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;
import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.repositories.IFinanceRepository;
import com.feliperodsdev.ms.financeservice.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    private IFinanceRepository repository;

    public FinanceService(@Qualifier("FinanceRepository") IFinanceRepository repository){
        this.repository = repository;
    }

    public void createPayment(CreatePaymentDto createPaymentDto){
        Payment payment = Payment.create(createPaymentDto.getValue(),
                createPaymentDto.getReferenceId(),
                createPaymentDto.getType());

        repository.save(payment);
    }

    public List<Payment> getAllPayments(){
        return repository.getAllPayments();
    }

    public Payment findByIdPayment(Long id){
        return repository.findByIdPayment(id).orElseThrow(() -> new ResourceNotFound());
    }

    public void cancelPayment(Long id){
        Payment payment = findByIdPayment(id);
        payment.markAsCanceled();
        repository.save(payment);
    }

    public void markAsPaid(Long id, MarkAsPaidDto markAsPaidDto){
        Payment payment = findByIdPayment(id);
        payment.markAsPaid(markAsPaidDto.getPaymentMethod());
        repository.save(payment);
    }

    public void markAsRefund(Long id){
        Payment payment = findByIdPayment(id);
        payment.markAsRefund();
        repository.save(payment);
    }

    public ReportQuantityDto getReportQuantity(FinancialTransactionType type){
        List<Payment> paymentList = repository.getAllPayments().stream().filter(
                payment -> payment.getType().equals(type)
        ).collect(Collectors.toList());

        List<Payment> paidPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.PAID);

        List<Payment> waitingPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.WAITING_PAYMENT);

        List<Payment> refundPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.REFUND);

        List<Payment> canceledPaymentList = getPaymentsByStatus(paymentList, PaymentStatus.CANCELED);

        Double totalValuePayment = paymentList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        return new ReportQuantityDto(totalValuePayment, paymentList.size(),
                paidPaymentList.size(), waitingPaymentList.size(),
                refundPaymentList.size(), canceledPaymentList.size());

    }

    public ReportFinanceDto getReportFinance(FinancialTransactionType type, PaymentStatus status){
        List<Payment> paymentList = repository.getAllPayments().stream().filter(
                payment -> payment.getType().equals(type)
        ).collect(Collectors.toList());

        List<Payment> list = getPaymentsByStatus(paymentList, status);

        Double totalValue = list.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        List<Payment> paymentMoneyList = getPaymentsByMethod(paymentList, PaymentMethod.MONEY);
        Double valueMoney = paymentMoneyList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        List<Payment> paymentCreditList = getPaymentsByMethod(paymentList, PaymentMethod.CREDIT_CARD);
        Double valueCredit = paymentCreditList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        List<Payment> paymentDebitList = getPaymentsByMethod(paymentList, PaymentMethod.DEBIT_CARD);
        Double debitCredit = paymentDebitList.stream()
                .mapToDouble(Payment::getValue)
                .sum();

        return new ReportFinanceDto(new ReportFinanceQuantityDto(list.size(),
                paymentMoneyList.size(), paymentCreditList.size(),
                paymentDebitList.size()), new ReportFinanceValueDto(totalValue, valueMoney,
                valueCredit, debitCredit));

    }

    public List<Payment> getPaymentsByStatus(List<Payment> paymentList, PaymentStatus status){
        return paymentList.stream().filter(
                        payment -> payment.getStatusPayment().equals(status))
                .collect(Collectors.toList());
    }

    public List<Payment> getPaymentsByMethod(List<Payment> paymentList, PaymentMethod method) {
        if(method == null) {
            return Collections.emptyList();
        }
        return paymentList.stream().filter(
                        payment -> payment.getPaymentMethod() == method)
                .collect(Collectors.toList());
    }

}
