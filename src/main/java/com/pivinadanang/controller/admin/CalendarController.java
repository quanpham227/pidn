package com.pivinadanang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/calendar")
public class CalendarController {
    @GetMapping("")
    public ModelAndView home (){
        return new ModelAndView("admin/calendar/calendar");
    }
}
