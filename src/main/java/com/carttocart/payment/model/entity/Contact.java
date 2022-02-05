package com.carttocart.payment.model.entity;

import com.carttocart.payment.model.common.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact extends BaseModel<Long> {

    private String mobileNumber;
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return super.getId();
    }

    @Column(name = "mobile_number")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
