package com.bot.config;

import com.bot.web.ManagementController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by itimofeev on 16.06.2016.
 */

@Configuration
public class WebConfig {

    @Bean
    public ManagementController managementController() {
        return new ManagementController();
    }


}
