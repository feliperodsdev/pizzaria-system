package com.feliperodsdev.ms.financeservice.dtos;

import com.feliperodsdev.ms.financeservice.enums.FinancialTransactionType;

public class CreatePaymentDto {

    private Double value;
    private FinancialTransactionType type;
    private Long referenceId;

    public CreatePaymentDto(Double value, FinancialTransactionType type, Long referenceId) {
        this.value = value;
        this.type = type;
        this.referenceId = referenceId;
    }

    public Double getValue() {
        return value;
    }

    public FinancialTransactionType getType() {
        return type;
    }

    public Long getReferenceId() {
        return referenceId;
    }

}
