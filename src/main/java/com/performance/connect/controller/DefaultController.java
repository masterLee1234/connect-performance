package com.performance.connect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping

@Controller
public class Default {
   @RequestMapping()
   public @ResponseBody def() {
      return "default";
   }
}
