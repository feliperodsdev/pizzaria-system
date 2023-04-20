package com.feliperodsdev.ms.financeservice.enums;

public enum PaymentMethod {

    CREDIT_CARD(1),
    DEBIT_CARD(2),
    MONEY(3);

    private int code;

    PaymentMethod(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static PaymentMethod valueOf(int code){
        for(PaymentMethod value: PaymentMethod.values()){
            if(value.getCode() == code){return value;}
        }
        throw new IllegalArgumentException("Invalid Payment Method Code");
    }

}
