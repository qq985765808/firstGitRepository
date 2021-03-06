package com.wupeng.demo.service;

import com.wupeng.demo.dto.OrderDto;
import com.wupeng.demo.pojo.OrderInfo;

import java.util.List;

public interface OrderInfoService  {

    public List<OrderInfo> findAll();

    public  void saveOrderInfo(OrderInfo orderInfo);

    public List<OrderDto> getOrderInfoNameAndTime();
}
