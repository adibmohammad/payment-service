package com.carttocart.payment.model.enums;

public enum BankTransactionStatusEnum {

    SUCCESSFUL(1), UNSUCCESSFUL(2);

    private Integer status;

    BankTransactionStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
