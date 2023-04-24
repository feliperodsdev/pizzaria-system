package com.feliperodsdev.ms.financeservice.services.memory;

import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.repositories.IFinanceRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDB implements IFinanceRepository {

    private List<Payment> paymentList = new ArrayList<>();

    public InMemoryDB(){}

    @Override
    public void save(Payment payment) {
        paymentList.add(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentList;
    }

}
