package com.example.spike.MQ.Producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class MessageSendController {
    private Logger logger = LoggerFactory.getLogger(MessageSendController.class);

    @Inject
    private RedisTemplate redisTemplate;

    @Autowired
    private MsgProducer msgProducer;

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestBody String msg) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        //包装成消息
        Object adminResult = redisTemplate.opsForHash().get("admin",msg);
        Message message=new Message("test_topic_2","test",adminResult.toString().getBytes());
        //调用配置好的DefaultMQProducer发送消息
        SendResult result=msgProducer.getMqProducer().send(message,3000);
        return msg;
    }
}

