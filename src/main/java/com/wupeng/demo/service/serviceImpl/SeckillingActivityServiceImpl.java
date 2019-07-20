package com.wupeng.demo.service.serviceImpl;

import com.wupeng.demo.repository.SeckillingActivityRepository;
import com.wupeng.demo.service.SeckillingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SeckillingActivityServiceImpl implements SeckillingActivityService {

    @Autowired
    private SeckillingActivityRepository seckillingActivityRepository;

    public  int updateSeckillingActivityProductsNumSubOneById(Long seckillingActivityId){
        return  0;
    }
}
