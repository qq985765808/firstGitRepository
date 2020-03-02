package com.wupeng.demo.support.rabbitMQ;

import com.wupeng.demo.config.RabbitMQConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQSender {

    private  static Log log  = LogFactory.getLog(RabbitMQSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendDirect() {
        String  msg = "sendDirect";
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_QUEUE,msg);
    }

    public void sendTopic1() {
        String  msg = "TopicMsg";
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_QUEUE_1,true);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_QUEUE_2,true);
    }
    public void sendTopic() {
        String  msg = "TopicMsg";
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE,"topic.key1",msg+"_1");
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE,"topic.key2",msg+"_2");
    }

    public void sendFanout() {
        String  msg = "FanoutMsg";
        log.info(msg);
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE,"",msg);
    }

    public void sendHeaders() {
        String msg = "HeadersMsg";
        log.info(msg);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("h1","v1");
        messageProperties.setHeader("h2","v2");
        Message message = new Message(msg.getBytes(),messageProperties);
        rabbitTemplate.convertAndSend(RabbitMQConfig.HEADERS_EXCHANGE,"",message);
    }
}
