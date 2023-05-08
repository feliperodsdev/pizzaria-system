package com.feliperodsdev.ms.financeservice.dtos;

public class ReportFinanceValueDto {

    private Double totalValue;
    private Double valueMoney;
    private Double valueCredit;
    private Double valueDebit;

    public ReportFinanceValueDto(Double totalValue, Double valueMoney,
                                 Double valueCredit, Double valueDebit) {
        this.totalValue = totalValue;
        this.valueMoney = valueMoney;
        this.valueCredit = valueCredit;
        this.valueDebit = valueDebit;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public Double getValueMoney() {
        return valueMoney;
    }

    public Double getValueCredit() {
        return valueCredit;
    }

    public Double getValueDebit() {
        return valueDebit;
    }

}
