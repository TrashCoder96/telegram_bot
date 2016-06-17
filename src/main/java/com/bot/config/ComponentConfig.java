package com.bot.config;

import com.bot.logic.Bot;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Created by itimofeev on 16.06.2016.
 */

@Configuration
public class ComponentConfig {

    @Bean
    public Bot bot() {
        return new Bot();
    }

    @Bean
    public org.springframework.scheduling.quartz.SchedulerFactoryBean schedulerFactoryBean() {
        return new org.springframework.scheduling.quartz.SchedulerFactoryBean();
    }


}
