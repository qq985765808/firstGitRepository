package com.wupeng.demo.repository;

import com.wupeng.demo.pojo.SeckillingActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface SeckillingActivityRepository  extends JpaRepository<SeckillingActivity,Long> {

    @Override
    List<SeckillingActivity> findAll();

    @Query(value = " SELECT * from  tab_seckilling_activity where seckilling_activity_beginTime  >= SYSDATE() and seckilling_activity_endTime <= SYSDATE() ",nativeQuery = true)
    public List<SeckillingActivity> getSeckillingActivityBySeckillingActivityBeginTimeAndAndSeckillingActivityEndTime();

    @Modifying
    @Query(value = "update tab_seckilling_activity set seckilling_activity_productsNum = seckilling_activity_productsNum -1 " +
            " where seckilling_activity_id = ?1  and seckilling_activity_productsNum > 0  and seckilling_activity_beginTime  >= SYSDATE() " +
            " and seckilling_activity_endTime <= SYSDATE() ",nativeQuery = true)
    public  int updateSeckillingActivityProductsNumSubOneById(Long seckillingActivityId);

}
