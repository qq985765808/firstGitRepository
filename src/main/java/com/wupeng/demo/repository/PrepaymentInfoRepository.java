package com.wupeng.demo.repository;

import com.wupeng.demo.pojo.PrepaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrepaymentInfoRepository extends JpaRepository<PrepaymentInfo,Long> {

    @Override
    List<PrepaymentInfo> findAll();
}
