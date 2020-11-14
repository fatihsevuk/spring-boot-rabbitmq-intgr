package com.ifelseco.consumemessage;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageReceiver {

    //@RabbitListener(queues="${ifelseco.rabbitmq.queueName}")
    public void listen(String msg) {
        System.out.println("Recieved Message: " + msg);
    }

}
