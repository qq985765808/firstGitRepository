package com.wupeng.demo.service;

import com.wupeng.demo.pojo.SeckillingActivity;

import java.util.List;

public interface SeckillingActivityService {
    public  int updateSeckillingActivityProductsNumSubOneById(Long seckillingActivityId);

    void save(SeckillingActivity seckillingActivity);

    SeckillingActivity getSeckillingActivityById(Long seckillingActivityId);

    List<SeckillingActivity> findAll();
}
