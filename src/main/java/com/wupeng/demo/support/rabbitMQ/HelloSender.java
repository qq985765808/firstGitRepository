package com.wupeng.demo.support.rabbitMQ;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

    private final  static Log log = LogFactory.getLog(HelloSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplete;

    public  void send(){
        String context = "hello"+new Date();
        log.info(context);
        this.rabbitTemplete.convertAndSend("test",context);
    }

}
