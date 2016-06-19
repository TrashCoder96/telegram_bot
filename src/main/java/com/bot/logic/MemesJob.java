package com.bot.logic;

import com.bot.BotApplication;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.io.IOException;

/**
 * Created by itimofeev on 17.06.2016.
 */

public class MemesJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Bot bot = (Bot) BotApplication.getCtx().getBean("bot");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(context.getJobDetail().getJobDataMap().getString("chat_id"));
        sendMessage.setText("Гайдай какой-то!");
        try {
            bot.sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
