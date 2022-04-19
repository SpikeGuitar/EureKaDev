package com.example.spike.MQ.Producer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MsgProducer {

    @Value("${apache.rocketmq.producer.producerGroup}")
    private String producerGroup;

    @Value("${apache.rocketmq.namesrvAddr}")
    private String namesrvAddr;

    private DefaultMQProducer mqProducer;

    //调用方注入MsgProducer后 通过这个方法获取配置好的DefaultMQProducer
    public DefaultMQProducer getMqProducer(){
        return mqProducer;
    }

    @PostConstruct
    public void initMQ(){
        mqProducer=new DefaultMQProducer(producerGroup);
        mqProducer.setNamesrvAddr(namesrvAddr);
        mqProducer.setVipChannelEnabled(false);
        try {
            mqProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destory(){
        mqProducer.shutdown();
    }
}

