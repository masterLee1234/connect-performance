package com.performance.connect.controller;

import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping(value="/Info/InfoUpdate")
    public ModelAndView InfoUpdate(CommandMap commandMap) throws Exception {

        ModelAndView mv = new ModelAndView("/Info/InfoUpdate");
        Map<String, Object> detail = InfoService.selectInfoDetail(commandMap.getMap());
        mv.addObject("detail",detail);
        return mv;
    }

    @RequestMapping(value="/Info/InfoUpdate", method=RequestMethod.POST)
    public ModelAndView InfoUpdatePOST(CommandMap commandMap) throws Exception {

        ModelAndView mv = new ModelAndView("redirect:/Info/InfoDetail");
        mv.addObject("idx", commandMap.get("idx"));
        InfoService.updateInfo(commandMap.getMap());
        return mv;
    }
    @RequestMapping(value="/Info/InfoDelete")
  public ModelAndView InfoDelete(CommandMap commandMap) throws Exception {
      ModelAndView mv = new ModelAndView("redirect:/Info/InfoList");
      InfoService.deleteInfo(commandMap.getMap());
      return mv;
  }
