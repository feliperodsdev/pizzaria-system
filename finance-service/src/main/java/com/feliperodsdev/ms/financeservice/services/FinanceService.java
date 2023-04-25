package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.CreatePaymentDto;
import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.repositories.IFinanceRepository;
import com.feliperodsdev.ms.financeservice.services.exceptions.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService {

    private IFinanceRepository repository;

    public FinanceService(IFinanceRepository repository){
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

}
