package com.zs.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tb_user")
public class User implements Serializable {
    @Id
    private String id;
    //昵称

    private String loginName ;
    private String password ;
    private String phone ;
    private String email;
    private int fans ;//粉丝数
    private Date registerTime ;
    private Date updateTime ;
    private Date lastLoginTime ;
    public User() {
    }
    public User(String loginName, String password, String phone, String email, int fans, Date registerTime, Date updateTime, Date lastLoginTime) {

        this.loginName = loginName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.fans = fans;
        this.registerTime = registerTime;
        this.updateTime = updateTime;
        this.lastLoginTime = lastLoginTime;
    }
    public User(String id, String loginName, String password, String phone, String email, int fans, Date registerTime, Date updateTime, Date lastLoginTime) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.fans = fans;
        this.registerTime = registerTime;
        this.updateTime = updateTime;
        this.lastLoginTime = lastLoginTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}