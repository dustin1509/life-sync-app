package com.dustin.consumer;

import com.dustin.config.SimpleDLQAmqpConfiguration;
import com.dustin.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class RoutingDLQAmqpContainer {
    private static final Logger log = LoggerFactory.getLogger(RoutingDLQAmqpContainer.class);

//    @RabbitListener(queues = SimpleDLQAmqpConfiguration.QUEUE_MESSAGES)
    public void receiveMessage(Message message) throws BusinessException {
        log.info("Received message: {}", message.toString());
        throw new BusinessException();
    }

}
