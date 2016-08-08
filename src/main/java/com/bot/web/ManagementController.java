package com.bot.web;

import com.bot.data.Comparison;
import com.bot.data.repository.ComparisonRepository;
import com.bot.logic.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by itimofeev on 16.06.2016.
 */

@Controller
public class ManagementController {

    @Autowired
    private ComparisonRepository comparisonRepository;

    @Autowired
    private Bot bot;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ModelAndView run() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("comparisons", comparisonRepository.findAll());
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam(required = true) String request, @RequestParam(required = true) String response) {
        Comparison comparison = new Comparison();
        comparison.setRequest(request);
        comparison.setResponse(response);
        comparisonRepository.save(comparison);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam(required = true) String id, @RequestParam(required = false) String request, @RequestParam(required = false) String response) {
        Comparison comparison = comparisonRepository.findOne(id);
        if (request != null) {
            comparison.setRequest(request);
        }
        if (response != null) {
            comparison.setResponse(response);
        }
        comparisonRepository.save(comparison);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(required = true) String id) {
        comparisonRepository.delete(id);
        return new ModelAndView("redirect:/");
    }



}
