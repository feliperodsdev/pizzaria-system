package com.feliperodsdev.ms.financeservice.enums;

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
        throw new IllegalArgumentException("Invalid Financial Type Code");
    }

}
