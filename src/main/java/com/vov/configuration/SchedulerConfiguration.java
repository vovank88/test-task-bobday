package com.vov.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class SchedulerConfiguration implements SchedulingConfigurer {

  @Value("${scheduler.poolsize:1000}")
  private int poolSize;

  @Override
  public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
    ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
    threadPoolTaskScheduler.setPoolSize(poolSize);
    threadPoolTaskScheduler.initialize();
    scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
  }
}