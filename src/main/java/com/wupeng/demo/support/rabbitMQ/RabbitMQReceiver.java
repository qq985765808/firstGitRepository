package com.wupeng.demo.support.rabbitMQ;

import com.wupeng.demo.config.RabbitMQConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQReceiver {

    private  static Log log = LogFactory.getLog(RabbitMQReceiver.class);

    @RabbitListener(queues = RabbitMQConfig.DIRECT_QUEUE)
    public  void receiverDirect(String msg){
        log.info("Direct Receive : " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_1)
    public void receiveTopic1(String msg) {
        log.info("Topic1 Receive : " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE_2)
    public void receiveTopic2(String msg) {
        log.info("Topic2 Receive : " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_1)
    public void receiveFanout1(String msg) {
        log.info("Fanout1 Receive : " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.FANOUT_QUEUE_2)
    public void receiveFanout2(String msg) {
        log.info("Fanout2 Receive : " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.HEADERS_QUEUE)
    public void receiveHeaders(byte[] msg) {
        log.info("Headers Receive : " + new String(msg));
    }
}
