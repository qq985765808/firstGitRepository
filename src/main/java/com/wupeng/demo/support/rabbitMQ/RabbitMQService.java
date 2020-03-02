package com.wupeng.demo.support.rabbitMQ;

import com.wupeng.demo.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;


/**
 * RabbitMQService：rabbitMQ的服务类
 * */
@Service
public class RabbitMQService implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(RabbitMQService.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendDirect(String msg) {
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_QUEUE,msg);
    }

    public void sendTopic1(String msg) {
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_QUEUE_1,true);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_QUEUE_2,true);
    }
    public void sendTopic(String msg) {
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE,"topic.key1",msg+"_1");
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE,"topic.key2",msg+"_2");
    }

    public void sendFanout(String msg) {
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE,"",msg);
    }

    public void sendHeaders(String msg) {
        log.info(msg);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("h1","v1");
        messageProperties.setHeader("h2","v2");
        Message message = new Message(msg.getBytes(),messageProperties);
        rabbitTemplate.convertAndSend(RabbitMQConfig.HEADERS_EXCHANGE,"",message);
    }



}
