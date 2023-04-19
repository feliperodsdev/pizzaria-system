package com.feliperodsdev.ms.financeservice.model;

import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;
import com.feliperodsdev.ms.financeservice.model.exceptions.EntityValidationException;

import javax.persistence.*;

@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double value;
    private PaymentStatus statusPayment;
    private PaymentMethod paymentMethod;
    private Long referenceId;

    public Payment(){}

    public static Payment create(Double value, Long referenceId){
        Payment payment = new Payment();

        if(!payment.isValidReferenceId(referenceId)) throw new EntityValidationException("'referenceId' is invalid.");

        payment.referenceId = referenceId;
        payment.value = value;
        payment.statusPayment = PaymentStatus.WAITING_PAYMENT;
        payment.paymentMethod = null;

        return payment;
    }

    public boolean isValidReferenceId(Long id){
        return id == null || id < 0;
    }

    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public PaymentStatus getStatusPayment() {
        return statusPayment;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Long getReferenceId() {
        return referenceId;
    }
}
