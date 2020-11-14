package com.ifelseco.consumemessage.config;


import com.ifelseco.consumemessage.RabbitMQMessageReceiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${ifelseco.rabbitmq.exchange}")
    private String topicExchangeName;

    @Value("${ifelseco.rabbitmq.routingKey}")
    private String routingKey;

    @Value("${ifelseco.rabbitmq.queueName}")
    private String queueName;


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }



    // or use @RabbitListener in Receiver class
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapter(RabbitMQMessageReceiver listener) {
        return new MessageListenerAdapter(listener, "listen");
    }


}
