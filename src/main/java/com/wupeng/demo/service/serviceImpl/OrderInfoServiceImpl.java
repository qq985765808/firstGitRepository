package com.wupeng.demo.service.serviceImpl;

import com.wupeng.demo.pojo.OrderInfo;
import com.wupeng.demo.repository.OrderInfoRepository;
import com.wupeng.demo.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoRepository orderInfoRepository;


    @Override
    public List<OrderInfo> findAll() {
        return orderInfoRepository.findAll();
    }

    @Override
    public void saveOrderInfo(OrderInfo orderInfo) {
         orderInfoRepository.save(orderInfo);
    }
}
