package com.wupeng.demo;

import com.wupeng.demo.support.rabbitMQ.HelloSender;
import com.wupeng.demo.support.rabbitMQ.RabbitMQSender;
import com.wupeng.demo.support.rabbitMQ.RabbitMQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private RabbitMQService rabbitMQService;



    @Test
    public void test() throws Exception {
        helloSender.send();
    }

    @Test
    public void contextLoads() {
        rabbitMQService.sendDirect("sendDirect");
        rabbitMQService.sendTopic("sendTopic");
        rabbitMQService.sendFanout("sendFanout");
        rabbitMQService.sendHeaders("sendHeaders");
    }

    @Test
    public void arrangeCoins() {
        int n=28,temp = 1, num = 0;
        if (n == 1 && n < 0) {
            System.out.println("次数为="+1);
            return;
        } else {
            for (int i = 1; i < n; i++) {
                if (n >= temp) {
                    temp += i + 1;
                    num++;
                } else {
                    System.out.println("次数为="+num);
                    return;
                }

            }
        }
    }
}