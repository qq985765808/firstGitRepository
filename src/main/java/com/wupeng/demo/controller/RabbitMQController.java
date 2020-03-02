package com.wupeng.demo.controller;

import com.wupeng.demo.support.rabbitMQ.RabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * rabbitMQ控制器
 * */
@RestController
@RequestMapping(value = "/rabbitMQ")
public class RabbitMQController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RabbitMQController.class);

    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping(value = "/sendMessage")
    public void sendMessage(String msg){
        log.info("点对点发送的消息={}",msg);
        rabbitMQService.sendDirect(msg);
    }
}
