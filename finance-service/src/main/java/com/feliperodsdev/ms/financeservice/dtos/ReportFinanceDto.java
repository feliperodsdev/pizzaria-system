package com.feliperodsdev.ms.financeservice.dtos;

import java.io.IOException;
import java.io.InputStream;

public class ReportFinanceDto {

    private ReportFinanceQuantityDto quantitySession;
    private ReportFinanceValueDto valueSession;

    public ReportFinanceDto() {
    }

    public ReportFinanceDto(ReportFinanceQuantityDto quantitySession, ReportFinanceValueDto valueSession) {
        this.quantitySession = quantitySession;
        this.valueSession = valueSession;
    }

    public ReportFinanceQuantityDto getQuantitySession() {
        return quantitySession;
    }

    public ReportFinanceValueDto getValueSession() {
        return valueSession;
    }
}
