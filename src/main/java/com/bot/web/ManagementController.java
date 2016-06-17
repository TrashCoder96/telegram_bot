package com.bot.web;

import com.bot.logic.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by itimofeev on 16.06.2016.
 */

@Controller
public class ManagementController {

    @Autowired
    private Bot bot;

    @RequestMapping(value = {"/" , "/run"}, method = RequestMethod.GET)
    public ModelAndView run() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("text", "runned");
        return model;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public ModelAndView stop() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("text", "stopped");
        return model;
    }



}
