package com.feliperodsdev.ms.financeservice.dtos;

public class ReportQuantityDto {

    private Double totalValue;
    private Integer totalQuantity;
    private Integer quantityPaid;
    private Integer quantityWaitingPayment;
    private Integer quantityRefund;
    private Integer quantityCanceled;

    public ReportQuantityDto(Double totalValue, Integer totalQuantityRevenues, Integer quantityPaid, Integer quantityWaitingPayment, Integer quantityRefund, Integer quantityCanceled) {
        this.totalValue = totalValue;
        this.totalQuantity = totalQuantityRevenues;
        this.quantityPaid = quantityPaid;
        this.quantityWaitingPayment = quantityWaitingPayment;
        this.quantityRefund = quantityRefund;
        this.quantityCanceled = quantityCanceled;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public Integer getQuantityPaid() {
        return quantityPaid;
    }

    public Integer getQuantityWaitingPayment() {
        return quantityWaitingPayment;
    }

    public Integer getQuantityRefund() {
        return quantityRefund;
    }

    public Integer getQuantityCanceled() {
        return quantityCanceled;
    }
}
