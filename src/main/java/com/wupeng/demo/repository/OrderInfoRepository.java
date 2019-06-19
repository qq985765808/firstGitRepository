package com.wupeng.demo.repository;

import com.wupeng.demo.dto.OrderDto;
import com.wupeng.demo.pojo.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo,Long> {

    @Override
    List<OrderInfo> findAll();

    @Override
    <S extends OrderInfo> S save(S s);

    @Query(value = "select new com.wupeng.demo.dto.OrderDto(o.orderId,o.orderSysNum,o.orderTime)from  OrderInfo o")
    public List<OrderDto> getOrderInfoNameAndTime();
}
