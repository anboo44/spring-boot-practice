package com.uet.spring.practice.eventSourcing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
public class ListenerConfiguration {

    @Bean
    TaskExecutor executeTask() {
        return new SimpleAsyncTaskExecutor();
    }
}
