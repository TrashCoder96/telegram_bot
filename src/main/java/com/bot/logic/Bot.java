package com.bot.logic;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;

import javax.annotation.PostConstruct;

/**
 * Created by itimofeev on 16.06.2016.
 */

@Component("bot")
public class Bot extends org.telegram.telegrambots.bots.TelegramLongPollingBot {

    @Autowired
    private org.springframework.scheduling.quartz.SchedulerFactoryBean schedulerFactoryBean;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.name}")
    private String botUsername;

    @Value("${telegram.bot.hello_text}")
    private String hello_text;

    @Value("${telegram.bot.error_text}")
    private String error_text;

    private TelegramBotsApi telegramBotsApi;

    @PostConstruct
    public void onPost() throws TelegramApiException {
        telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help"))
                sendMsg(message, hello_text);
            else
                sendMsg(message, error_text);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplayToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /*public void run() {
        JobDetail job = JobBuilder.newJob(MessageJob.class).withIdentity("job").build();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
        try {
            this.schedulerFactoryBean.getScheduler().scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            this.schedulerFactoryBean.getScheduler().unscheduleJob(TriggerKey.triggerKey("trigger"));
            this.schedulerFactoryBean.getScheduler().deleteJob(JobKey.jobKey("job"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }*/



}
