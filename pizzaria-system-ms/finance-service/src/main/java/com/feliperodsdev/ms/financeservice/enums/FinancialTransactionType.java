package com.feliperodsdev.ms.financeservice.enums;

import com.feliperodsdev.ms.financeservice.enums.exceptions.InvalidCode;

public enum FinancialTransactionType {

    REVENUE(1),
    EXPENSE(2);

    private int code;

    FinancialTransactionType(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static FinancialTransactionType valueOf(int code){
        for(FinancialTransactionType value: FinancialTransactionType.values()){
            if(value.getCode() == code){return value;}
        }
        throw new InvalidCode("Invalid Financial Type Code");
    }

}
