package com.wupeng.demo.service;

import com.wupeng.demo.pojo.UserInfo;

import java.util.List;

public interface UserInfoService {

    public List<UserInfo> findAll();

    public  void  saveUserInfo(UserInfo userInfo);

    public List<UserInfo>  getUserInfoByUserNameAndPassword(String userName,String password);
}
