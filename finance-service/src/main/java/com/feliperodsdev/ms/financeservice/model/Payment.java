package com.feliperodsdev.ms.financeservice.model;

import com.feliperodsdev.ms.financeservice.enums.PaymentMethod;
import com.feliperodsdev.ms.financeservice.enums.PaymentStatus;

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

}
