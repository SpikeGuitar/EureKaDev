package com.example.spike.PoJo;

import lombok.Data;

@Data
public class RocketMQVO {
    //MQ Topic
    private String topic;

    //MQ Tag
    private String tag;

    //MQ message
    private Object msg;
}