package com.wupeng.demo.service.serviceImpl;

import com.wupeng.demo.pojo.RecordIp;
import com.wupeng.demo.repository.RecordIpRepository;
import com.wupeng.demo.service.RecordIpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RecordIpServiceImpl implements RecordIpService {

    @Autowired
    private RecordIpRepository recordIpRepository;

    @Override
    public void saveEntity(RecordIp recordIp) {
        recordIpRepository.save(recordIp);
    }
}
