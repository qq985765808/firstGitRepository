package com.wupeng.demo.repository;

import com.wupeng.demo.pojo.RecordIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordIpRepository extends JpaRepository<RecordIp,Long> {

    @Override
    <S extends RecordIp> S save(S s);
}
