package com.wupeng.demo.repository;

import com.wupeng.demo.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    @Override
    List<UserInfo> findAll();

    @Override
    <S extends UserInfo> S save(S s);

    @Query(value = "select  * from userinfo.tab_user_info where user_name = ?1 and user_password = ?2",nativeQuery = true)
    List<UserInfo>  getUserInfoByUserNameAndPassword(String userName,String password);

    @Modifying
    @Query(value = "update userinfo.tab_user_info set user_password = ?1 where user_name = ?2",nativeQuery = true)
    public int updateUserInfoByUserName(String password,String userName);
}
