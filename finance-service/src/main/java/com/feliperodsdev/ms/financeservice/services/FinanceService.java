package com.feliperodsdev.ms.financeservice.services;

import com.feliperodsdev.ms.financeservice.dtos.CreatePaymentDto;
import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.repositories.IFinanceRepository;
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

}
