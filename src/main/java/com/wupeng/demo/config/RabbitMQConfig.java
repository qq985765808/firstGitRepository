package com.wupeng.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * Rabbit配置类
 * */

@Configuration
public class RabbitMQConfig {

    /**
     * Direct模式：是直接用队列的名字来进行绑定，实现点对点的消息传输
     * Topic模式：是根据 Config 中设置的 RoutineKey 还有发送消息时候的 topic 来判断是否会传输
     * Fanout模式：类似于广播，不用设置路由，只要发送消息设置了对应的 Exchange 就可以对该 Exchange 中的接收者进行广播
     * Header模式：Headers 是一个键值对，可以定义成 Hashtable。发送者在发送的时候定义一些键值对，
     * 接收者也可以再绑定时候传入一些键值对，两者匹配的话，则对应的队列就可以收到消息。匹配有两种方式all和any
     * */
    //四种模式：Direct，Topic，Fanout，Header
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String ROUTINE_KEY_1 = "topic.key1";

    // 可以使用通配符
    public static final String ROUTINE_KEY_2 = "topic.*";

    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";
    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    public static final String HEADERS_QUEUE = "headers_queue";
    public static final String HEADERS_EXCHANGE = "headers_exchange";


    /**
     * 测试queue
     * */
    @Bean
    public Queue Queue(){
        return new Queue("test");
    }

    /**
     * Direct模式
     * */
    @Bean
    public Queue directQueue(){
        return  new Queue(DIRECT_QUEUE,true);
    }

    /**
     * Topic模式：根据RoutineKey去绑定接收的消息
     * */
    @Bean
    public  Queue topicQueue1(){
        return  new Queue(TOPIC_QUEUE_1,true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2, true);
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTINE_KEY_1);
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTINE_KEY_2);
    }

    /**
     * Fanout模式：广播
     * */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1, true);
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2, true);
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }

    /**
     *Headers模式：只有检验头部的KV是一致的才会接收到消息
     * */
    @Bean
    public HeadersExchange headersExchage(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }
    @Bean
    public Queue headerQueue() {
        return new Queue(HEADERS_QUEUE, true);
    }
    @Bean
    public Binding headerBinding() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("h1", "v1");
                map.put("h2", "v2");
                return BindingBuilder.bind(headerQueue()).to(headersExchage()).whereAll(map).match();
                }
                }
