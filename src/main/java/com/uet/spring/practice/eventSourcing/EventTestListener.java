package com.uet.spring.practice.eventSourcing;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EventTestListener implements ApplicationListener<EventMessage> {

    @Async
    @Override
    public void onApplicationEvent(EventMessage event) {
        System.out.printf("========= [ Receive Message: %s ]=============\n", event.getMessage());
    }
}
