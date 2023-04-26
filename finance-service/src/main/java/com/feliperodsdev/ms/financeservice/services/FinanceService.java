package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.CreatePaymentDto;
import com.feliperodsdev.ms.financeservice.dtos.MarkAsPaidDto;
import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.repositories.IFinanceRepository;
import com.feliperodsdev.ms.financeservice.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
