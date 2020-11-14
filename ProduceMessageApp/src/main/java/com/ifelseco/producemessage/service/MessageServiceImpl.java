package com.ifelseco.producemessage.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private  RabbitTemplate rabbitTemplate;

    @Value("${ifelseco.rabbitmq.exchange}")
    private String topicExchangeName;

    @Value("${ifelseco.rabbitmq.routingKey}")
    private String routingKey;

    @Autowired
    public MessageServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(topicExchangeName,routingKey,message);
    }


}
