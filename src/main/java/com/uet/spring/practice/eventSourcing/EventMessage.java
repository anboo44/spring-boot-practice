package com.uet.spring.practice.eventSourcing;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

public class EventMessage extends ApplicationEvent {
    private String message;

    public EventMessage(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
