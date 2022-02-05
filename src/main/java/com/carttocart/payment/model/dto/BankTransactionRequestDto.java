package com.carttocart.payment.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class BankTransactionRequestDto {

    @NotNull(message = "accountId is mandatory")
    private Long accountId;
    @NotBlank(message = "cardNumber is mandatory")
    @Length(min = 19, max = 19, message = "the length of destinationCardNumber should be 19")
    private String destinationCardNumber;
    @NotBlank(message = "cvv2 is mandatory")
    @Min(value = 100, message = "cvv2 must be bigger then 100")
    @Max(value = 9999, message = "cvv2 must be lower then 9999")
    private String cvv2;
    @NotBlank(message = "expireDate is mandatory")
    @Pattern(regexp = "[0-9]{6}")
    private String expireDate;
    @NotBlank(message = "pinCode is mandatory")
    private String pin;
    @NotNull(message = "amount is mandatory")
    private Long amount;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDestinationCardNumber() {
        return destinationCardNumber;
    }

    public void setDestinationCardNumber(String destinationCardNumber) {
        this.destinationCardNumber = destinationCardNumber;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
