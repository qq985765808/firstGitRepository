package com.wupeng.demo.service.serviceImpl;

import com.wupeng.demo.service.PrepaymentInfoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service(value = "prepaymentInfoService")
@Transactional
public class PrepaymentInfoServiceImpl implements PrepaymentInfoService {

}
