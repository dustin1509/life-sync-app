package com.dustin.producer;

import com.dustin.model.entity.SystemUser;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${dustin.rabbitmq.exchange}")
    private String exchange;

    @Value("${dustin.rabbitmq.routingkey}")
    private String routingkey;

    public void send(SystemUser user) {
        rabbitTemplate.convertAndSend(exchange, routingkey, user);
        System.out.println("Send msg = " + user);
    }
}
