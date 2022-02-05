package com.carttocart.payment.model.entity;

import com.carttocart.payment.model.common.AuditModel;

import javax.persistence.*;

@Entity
@Table(name = "bank_transaction")
public class BankTransaction extends AuditModel<Long> {

    private BankAccount fromCard;
    private String toCardNumber;
    private Integer status;
    private Long amount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_card_id")
    public BankAccount getFromCard() {
        return fromCard;
    }

    public void setFromCard(BankAccount fromCard) {
        this.fromCard = fromCard;
    }

    @Column(name = "to_card_number")
    public String getToCardNumber() {
        return toCardNumber;
    }

    public void setToCardNumber(String toCardNumber) {
        this.toCardNumber = toCardNumber;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "amount")
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
