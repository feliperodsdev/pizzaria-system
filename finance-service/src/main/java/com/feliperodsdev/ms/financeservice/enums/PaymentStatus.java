package com.feliperodsdev.ms.financeservice.enums;

public enum PaymentStatus {

    WAITING_PAYMENT(1),
    CANCELED(2),
    REFUND(3),
    PAID(4);

    private int code;

    PaymentStatus(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    public static PaymentStatus valueOf(int code){
        for(PaymentStatus value: PaymentStatus.values()){
            if(value.getCode() == code){return value;}
        }
        throw new IllegalArgumentException("Invalid Payment Status Code");
    }

}
