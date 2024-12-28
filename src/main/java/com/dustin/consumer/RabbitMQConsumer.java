package com.dustin.consumer;

import com.dustin.model.entity.SystemUser;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
//    @RabbitListener(queues = "${dustin.rabbitmq.queue}")
    public void receivedMessage(SystemUser user) {
        System.out.println("A Test Received Message From RabbitMQ: " + user);
    }
}
