package com.performance.connect.controller;

import com.performance.connect.domain.Performance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class MainController {
    @GetMapping(value = "/")
    public String index(Model model) {
        



        MemberVO member = new MemberVO();
        member.setUserid("userid-01");
        member.setUserpw("userpw-01");

        model.addAttribute("member", member);
        logger.info("MemberVO : "+member.toString());

        return "templates/data";
    }

    @RequestMapping("/index")
    public String index(Model model){


        Map<String,String> map = new HashMap<String,String>();
        map.put("userid", "userid-02");
        map.put("userpw", "userpw-02");

        model.addAttribute("map", map);

        return "templates/data";
    }

    @GetMapping(value = "/post_performance")
    public String getPerformance() {
        System.out.println("asdf");
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

}
