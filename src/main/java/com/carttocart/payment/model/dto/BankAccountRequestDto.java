package com.carttocart.payment.model.dto;

import com.carttocart.payment.config.Config;
import com.carttocart.payment.model.entity.BankAccount;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class BankAccountRequestDto {

    @NotBlank(message = "accountNumber is mandatory")
    private String accountNumber;
    @NotBlank(message = "cardNumber is mandatory")
    @Length(min = 19, max = 19, message = "the length of cardNumber should be 19")
    private String cardNumber;
    @NotNull(message = "cvv2 is mandatory")
    @Min(value = 100 , message = "cvv2 must be bigger then 100")
    @Max(value = 9999 ,message = "cvv2 must be lower then 9999")
    private Integer cvv2;
    @NotNull(message = "pinCode is mandatory")
    private Long pinCode;
    @NotBlank(message = "cardNumber is mandatory")
    @Pattern(regexp="^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", message="the value must be yyyy-mm-dd")
    private String expireDate;

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

    public Integer getCvv2() {
        return cvv2;
    }

    public void setCvv2(Integer cvv2) {
        this.cvv2 = cvv2;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public static BankAccount convertToEntity(BankAccountRequestDto dto) {
        return Config.modelMapper().map(dto, BankAccount.class);
    }
}
