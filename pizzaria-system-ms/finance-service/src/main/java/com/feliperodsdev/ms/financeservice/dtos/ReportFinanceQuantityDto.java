package com.feliperodsdev.ms.financeservice.dtos;

public class ReportFinanceQuantityDto {

    private Integer quantityPayments;
    private Integer quantityPaymentMoney;
    private Integer quantityPaymentCredit;
    private Integer quantityPaymentDebit;

    public ReportFinanceQuantityDto() {
    }

    public ReportFinanceQuantityDto(Integer quantityPayments, Integer quantityPaymentMoney,
                                    Integer quantityPaymentCredit, Integer quantityPaymentDebit) {
        this.quantityPayments = quantityPayments;
        this.quantityPaymentMoney = quantityPaymentMoney;
        this.quantityPaymentCredit = quantityPaymentCredit;
        this.quantityPaymentDebit = quantityPaymentDebit;
    }

    public Integer getQuantityPayments() {
        return quantityPayments;
    }

    public void setQuantityPayments(Integer quantityPayments) {
        this.quantityPayments = quantityPayments;
    }

    public Integer getQuantityPaymentMoney() {
        return quantityPaymentMoney;
    }

    public void setQuantityPaymentMoney(Integer quantityPaymentMoney) {
        this.quantityPaymentMoney = quantityPaymentMoney;
    }

    public Integer getQuantityPaymentCredit() {
        return quantityPaymentCredit;
    }

    public void setQuantityPaymentCredit(Integer quantityPaymentCredit) {
        this.quantityPaymentCredit = quantityPaymentCredit;
    }

    public Integer getQuantityPaymentDebit() {
        return quantityPaymentDebit;
    }

    public void setQuantityPaymentDebit(Integer quantityPaymentDebit) {
        this.quantityPaymentDebit = quantityPaymentDebit;
    }

}
