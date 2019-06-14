package com.wupeng.demo.service.serviceImpl;

import com.wupeng.demo.pojo.UserInfo;
import com.wupeng.demo.repository.UserInfoRepository;
import com.wupeng.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
          userInfoRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> getUserInfoByUserNameAndPassword(String userName, String password) {
        return userInfoRepository.getUserInfoByUserNameAndPassword(userName,password);
    }
}
