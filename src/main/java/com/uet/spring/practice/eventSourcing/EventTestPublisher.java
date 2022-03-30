package com.uet.spring.practice.eventSourcing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventTestPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMessage(String message) {
        System.out.printf("===========[ Publish message: %s ]==============\n", message);
        applicationEventPublisher.publishEvent(new EventMessage(this, message));
    }
}
