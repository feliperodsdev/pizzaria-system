package com.feliperodsdev.ms.financeservice.repositories;

import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.services.FinanceService;

import java.util.List;
import java.util.Optional;

public interface IFinanceRepository {

    void save(Payment payment);
    List<Payment> getAllPayments();
    Optional<Payment> findByIdPayment(Long id);

}
