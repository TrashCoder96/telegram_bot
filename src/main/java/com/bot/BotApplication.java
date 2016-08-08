package com.bot;

import com.bot.config.ComponentConfig;
import com.bot.config.DatabaseConfig;
import com.bot.config.WebConfig;
import com.bot.data.Account;
import com.bot.data.Comparison;
import com.bot.data.repository.AccountRepository;
import com.bot.data.repository.ComparisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * Created by itimofeev on 16.06.2016.
 */

@SpringBootApplication
@ComponentScan(basePackageClasses = { WebConfig.class, DatabaseConfig.class, ComponentConfig.class })
public class BotApplication extends SpringBootServletInitializer {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ComparisonRepository comparisonRepository;

    public static ApplicationContext getCtx() {
        return ctx;
    }

    private static ApplicationContext ctx;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BotApplication.class);
    }

    public static void main(String[] args) {
        ctx = SpringApplication.run(BotApplication.class, args);
    }

    @PostConstruct
    private void post() {
        if (accountRepository.findAll().size() == 0) {
            accountRepository.save(new Account());
        }

        if (comparisonRepository.findAll().size() == 0) {
            comparisonRepository.save(new Comparison());
        }
    }
}
