package com.feliperodsdev.ms.financeservice.services.memory;

import com.feliperodsdev.ms.financeservice.model.Payment;
import com.feliperodsdev.ms.financeservice.repositories.IFinanceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class InMemoryDB implements IFinanceRepository {

    private List<Payment> paymentList = new ArrayList<>();

    public InMemoryDB(){}

    @Override
    public void save(Payment payment) {
        Long randomLong = ThreadLocalRandom.current().nextLong(Long.MIN_VALUE, Long.MAX_VALUE);
        payment.setId(randomLong);
        paymentList.add(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentList;
    }

    @Override
    public Optional<Payment> findByIdPayment(Long id) {
        for(Payment payment: paymentList){
            if(payment.getId().equals(id)){
                return Optional.of(payment);
            }
        }
        return Optional.empty();
    }

}
