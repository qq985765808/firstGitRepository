package com.wupeng.demo.vo;

import java.io.Serializable;

public class Login  implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String userName;

    private  String password;

    private  String email;

    private  String phoneNum;

    public  Login(){
        super();
    }

    public Login(String userName, String password, String email, String phoneNum) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
