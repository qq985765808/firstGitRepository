package com.wupeng.demo.vo;

import java.io.Serializable;

public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private  String userName;
    private  String password;
    private  String resetPassword;
    private  String enterPassword;

    public UserInfoVO(){
        super();
    }

    public UserInfoVO(String userName, String password, String resetPassword, String enterPassword) {
        this.userName = userName;
        this.password = password;
        this.resetPassword = resetPassword;
        this.enterPassword = enterPassword;
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

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getEnterPassword() {
        return enterPassword;
    }

    public void setEnterPassword(String enterPassword) {
        this.enterPassword = enterPassword;
    }


}
