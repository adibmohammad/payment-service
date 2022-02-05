package com.carttocart.payment.model.dto;

import com.carttocart.payment.config.Config;
import com.carttocart.payment.model.entity.BankAccount;

import java.util.Date;

public class BankAccountResponseDto {

    private Long id;
    private String accountNumber;
    private String cardNumber;
    private Date expireDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public static BankAccountResponseDto convertToDto(BankAccount planComment) {
        return Config.modelMapper().map(planComment, BankAccountResponseDto.class);
    }
}
