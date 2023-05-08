package com.feliperodsdev.ms.financeservice.repositories;

import com.feliperodsdev.ms.financeservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceJPARespository extends JpaRepository<Payment, Long> {
}
