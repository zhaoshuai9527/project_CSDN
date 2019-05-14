package com.zs.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tb_admin")
public class Admin implements Serializable {
    @Id
    private String id ;
    //登录名
    private String loginName ;
    private String password ;
    private String phone ;
    private String email;

    public Admin() {
    }
    public Admin( String loginName, String password, String phone, String email) {
        this.loginName = loginName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
    public Admin(String id, String loginName, String password, String phone, String email) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}