package com.feliperodsdev.ms.financeservice.repositories;

import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.services.FinanceService;

import java.util.List;

public interface IFinanceRepository {

    void save(Payment payment);
    List<Payment> getAllPayments();

}
