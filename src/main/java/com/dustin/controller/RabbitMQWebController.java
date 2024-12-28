package com.dustin.controller;

import com.dustin.model.entity.SystemUser;
import com.dustin.producer.RabbitMQSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/javainuse-rabbitmq/")
@AllArgsConstructor
public class RabbitMQWebController {
    RabbitMQSender rabbitMQSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("username") String username,@RequestParam("id") Long id) {

        SystemUser emp=new SystemUser();
        emp.setId(id);
        emp.setUsername(username);
        rabbitMQSender.send(emp);

        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
