package com.carttocart.payment.model.entity;

import com.carttocart.payment.model.common.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bank_account")
public class BankAccount extends AuditModel<Long> {

    private String accountNumber;
    private String cardNumber;
    private Integer cvv2;
    private Long pinCode;
    private Boolean activeFlag;
    private Date expireDate;
    private Date disableDate;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column(name = "cvv2")
    public Integer getCvv2() {
        return cvv2;
    }

    public void setCvv2(Integer cvv2) {
        this.cvv2 = cvv2;
    }

    @Column(name = "pin_code")
    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }

    @Column(name = "active_flag")
    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    @Column(name = "expire_date")
    @Temporal(value = TemporalType.DATE)
    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    @Column(name = "disable_date")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
