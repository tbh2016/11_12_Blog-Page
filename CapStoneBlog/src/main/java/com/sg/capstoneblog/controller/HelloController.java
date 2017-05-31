package com.sg.capstoneblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    public HelloController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHi() {
        return "redirect:/Page/displayHomePage";
    }
}
