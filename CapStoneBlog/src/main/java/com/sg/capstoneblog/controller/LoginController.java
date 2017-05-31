/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.controller;

import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.service.LinkService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author chris
 */
@Controller
public class LoginController {

    LinkService linkService;

    @Inject
    public LoginController(LinkService linkService) {
        this.linkService = linkService;

    }

    @RequestMapping("/login")
    public String showLoginForm(Model model) {
        List<Link> navLinks = linkService.getMainLinks();

        model.addAttribute("navLinks", navLinks);

        return "login";
    }
}
