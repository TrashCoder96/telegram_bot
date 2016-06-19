package com.bot.logic;

import org.glassfish.grizzly.utils.ArrayUtils;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by itimofeev on 16.06.2016.
 */

@Component("bot")
public class Bot extends org.telegram.telegrambots.bots.TelegramLongPollingBot {

    @Autowired
    private org.springframework.scheduling.quartz.SchedulerFactoryBean schedulerFactoryBean;

    private List<Long> listOfChatIds;
    private List<String> listOfTriggers;
    private List<String> listOfJobs;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.name}")
    private String botUsername;

    @Value("${telegram.bot.hello_text}")
    private String hello_text;

    @Value("${telegram.bot.error_text}")
    private String error_text;

    @Value("${telegram.bot.cron}")
    private String cron;

    public TelegramBotsApi getTelegramBotsApi() {
        return telegramBotsApi;
    }

    private TelegramBotsApi telegramBotsApi;

    @PostConstruct
    public void onPost() throws TelegramApiException {
        telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(this);
        listOfChatIds = new ArrayList<Long>();
        listOfJobs = new ArrayList<String>();
        listOfTriggers = new ArrayList<String>();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        Boolean condition = message != null && message.hasText();
        try {
        if (condition && message.getText().equals("/start")) {
            this.listOfChatIds.add(chatId);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Вы подписаны на рассылку мемов");
            sendMessage.setChatId(chatId.toString());
            String triggerName = UUID.randomUUID().toString();
            String jobName = UUID.randomUUID().toString();
            this.listOfJobs.add(jobName);
            this.listOfTriggers.add(triggerName);
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName).withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            JobDetail job = JobBuilder.newJob(MemesJob.class).withIdentity(jobName).build();
            job.getJobDataMap().put("chat_id", chatId.toString());
            this.schedulerFactoryBean.getScheduler().scheduleJob(job, trigger);
            this.sendMessage(sendMessage);
            return;
        }

        if (condition && listOfChatIds.contains(chatId) && message.getText().equals("/mem")) {
            return;
        }
        if (condition && listOfChatIds.contains(chatId) && message.getText().equals("/stop")) {
            for (int i = 0; i < this.listOfChatIds.size(); i++) {
                if (this.listOfChatIds.get(i).equals(chatId)) {
                    this.listOfChatIds.remove(i);
                    this.schedulerFactoryBean.getScheduler().unscheduleJob(TriggerKey.triggerKey(this.listOfTriggers.remove(i)));
                    this.schedulerFactoryBean.getScheduler().deleteJob(JobKey.jobKey(this.listOfJobs.remove(i)));
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText("Вы отписаны от рассылки мемов");
                    sendMessage.setChatId(chatId.toString());
                    this.sendMessage(sendMessage);
                    return;
                }
            }

        }

        if (condition && listOfChatIds.contains(chatId) && message.getText().equals("/help")) {
            this.sendMsg(message, hello_text);
            return;
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(error_text);
        sendMessage.setChatId(chatId.toString());
        this.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (SchedulerException e) {
            e.printStackTrace();
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
