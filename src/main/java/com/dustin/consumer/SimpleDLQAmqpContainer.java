package com.dustin.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleDLQAmqpContainer {
    private static final Logger log = LoggerFactory.getLogger(SimpleDLQAmqpContainer.class);
    private final RabbitTemplate rabbitTemplate;

    public SimpleDLQAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
//    public void processFailedMessages(Message message) {
//        log.info("Received failed message: {}", message.toString());
//    }
//
//    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
//    public void processFailedMessagesRequeue(Message failedMessage) {
//        log.info("Received failed message, requeueing: {}", failedMessage.toString());
//        log.info("Received failed message, requeueing: {}", failedMessage.getMessageProperties().getReceivedRoutingKey());
//        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(), failedMessage);
//    }
}
