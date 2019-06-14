package com.wupeng.demo.repository;

import com.wupeng.demo.pojo.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderInfoRepository extends JpaRepository<OrderInfo,Long> {

    @Override
    List<OrderInfo> findAll();

    @Override
    <S extends OrderInfo> S save(S s);
}
