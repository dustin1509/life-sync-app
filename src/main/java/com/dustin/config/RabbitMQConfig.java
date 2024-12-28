package com.dustin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableRabbit
public class RabbitMQConfig {
    @Value("${dustin.rabbitmq.queue}")
    String queueName;

    @Value("${dustin.rabbitmq.exchange}")
    String exchange;

    @Value("${dustin.rabbitmq.routingkey}")
    private String routingkey;

    @Bean(name="dustin.queue")
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean(name = "dustin.exchange")
    DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(@Qualifier("dustin.queue") Queue queue, @Qualifier("dustin.exchange")DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
