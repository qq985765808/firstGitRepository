package com.wupeng.demo.service.serviceImpl;

import com.wupeng.demo.pojo.SeckillingActivity;
import com.wupeng.demo.repository.SeckillingActivityRepository;
import com.wupeng.demo.service.SeckillingActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SeckillingActivityServiceImpl implements SeckillingActivityService {

    @Autowired
    private SeckillingActivityRepository seckillingActivityRepository;

    public  int updateSeckillingActivityProductsNumSubOneById(Long seckillingActivityId){
        return seckillingActivityRepository.updateSeckillingActivityProductsNumSubOneById(seckillingActivityId);
    }

    @Override
    public void save(SeckillingActivity seckillingActivity) {
        seckillingActivityRepository.save(seckillingActivity);
    }

    @Override
    public SeckillingActivity getSeckillingActivityById(Long seckillingActivityId) {
        return seckillingActivityRepository.getOne(seckillingActivityId);
    }

    @Override
    public List<SeckillingActivity> findAll() {
        return seckillingActivityRepository.findAll();
    }
}
