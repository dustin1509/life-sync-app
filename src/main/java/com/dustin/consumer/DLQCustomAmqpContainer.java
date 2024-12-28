package com.dustin.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.dustin.config.SimpleDLQAmqpConfiguration.EXCHANGE_MESSAGES;
import static com.dustin.config.SimpleDLQAmqpConfiguration.QUEUE_MESSAGES_DLQ;

@Service
public class DLQCustomAmqpContainer {
    private static final Logger log = LoggerFactory.getLogger(DLQCustomAmqpContainer.class);
    private final RabbitTemplate rabbitTemplate;
    public static final int MAX_RETRIES_COUNT = 2;

    public static final String HEADER_X_RETRIES_COUNT = "x-retries-count";

    public DLQCustomAmqpContainer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @RabbitListener(queues = QUEUE_MESSAGES_DLQ)
    public void processFailedMessagesRetryHeaders(Message failedMessage) {
        Integer retriesCnt = (Integer) failedMessage.getMessageProperties().getHeaders().get(HEADER_X_RETRIES_COUNT);
        if (retriesCnt == null)
            retriesCnt = 1;
        if (retriesCnt > MAX_RETRIES_COUNT) {
            log.info("Discarding message");
            return;
        }
        log.info("=========Retrying message for the {} time", retriesCnt);
        failedMessage.getMessageProperties().getHeaders().put(HEADER_X_RETRIES_COUNT, ++retriesCnt);
        rabbitTemplate.send(EXCHANGE_MESSAGES, failedMessage.getMessageProperties().getReceivedRoutingKey(), failedMessage);
    }
}
