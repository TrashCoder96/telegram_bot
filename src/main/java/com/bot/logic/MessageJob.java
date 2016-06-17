package com.bot.logic;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;

/**
 * Created by itimofeev on 17.06.2016.
 */

public class MessageJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //Bot bot = (Bot)BotApplication.getCtx().getBean("bot");

       // bot.getBot().execute(new SendMessage("@mychannel", "message text"), new Callback<SendMessage, SendResponse>() {

        //    @Override
       //     public void onResponse(SendMessage request, SendResponse response) {
       //         System.out.println("Hello");
       //     }

        //    @Override
        //    public void onFailure(SendMessage request, IOException e) {

        //    }
        //});

    }
}
