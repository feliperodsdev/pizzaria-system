package com.feliperodsdev.ms.financeservice.repositories;

import com.feliperodsdev.ms.financeservice.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("FinanceRepository")
public class FinanceRepositoryImpl implements IFinanceRepository {

    @Autowired
    FinanceJPARespository repository;


    @Override
    public void save(Payment payment) {
        repository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    @Override
    public Optional<Payment> findByIdPayment(Long id) {
        return repository.findById(id);
    }
}
