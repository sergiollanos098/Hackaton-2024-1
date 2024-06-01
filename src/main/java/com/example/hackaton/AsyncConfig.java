package com.example.hackaton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@EnableAsync
public class AsyncConfig{

    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor(){return new SimpleAsyncTaskExecutor();}

    @EnableScheduling
    public class SchedulerConfig {
    }
}
