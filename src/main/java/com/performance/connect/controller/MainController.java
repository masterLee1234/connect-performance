package com.performance.connect.controller;

import com.performance.connect.domain.Performance;
import com.performance.connect.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    private PerformanceService performanceService;

    @Autowired
    public MainController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping(value = "/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "/post_performance")
    public String getPerformance() {
        return "post_performance";
    }

    @PostMapping(value = "/post_performance")
    public String postPerformance(PerformanceForm form) {
        Performance performance = new Performance();
        performance.setSchool(form.getSchool());
        performance.setGrade(form.getGrade());
        performance.setCls(form.getCls());
        performance.setSubject(form.getSubject());
        performance.setDue(form.getDue());
        performance.setTitle(form.getTitle());
        performance.setDesc(form.getDesc());

        performance.to_string();
        return "redirect:/";
    }
}
