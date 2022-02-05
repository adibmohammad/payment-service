package com.carttocart.payment.model.entity;

import com.carttocart.payment.model.common.BaseModel;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User extends BaseModel<Long>{

    private String fullName;
    private String username;
    private String password;
    private String mobileNumber;

    @Id
    public Long getId() {
        return super.getId();
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "mobile_number")
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
