package com.wupeng.demo.support.rabbitMQ;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "test")
public class HelloRecevier {

    private final static Log log = LogFactory.getLog(HelloRecevier.class);

    @RabbitHandler
    public void  process(String test){
        System.out.println("Recevier:"+test);
        log.info("Recevier:"+test);
    }
}
