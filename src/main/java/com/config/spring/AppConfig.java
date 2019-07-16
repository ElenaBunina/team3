package com.config.spring;

import com.dao.service.TaskProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

    SpringConfig s = new SpringConfig();
//    @Bean
//    public DAOImpl getDAO() {
//        return new DAOImpl(s.getDataSource());
//    }

    @Bean
    public TaskProcessor bean() {
        return new TaskProcessor(s.getDataSource());
    }
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }


    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(5);
    }
}