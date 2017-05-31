/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.controller;

import com.sg.capstoneblog.model.Comment;
import com.sg.capstoneblog.model.User;
import com.sg.capstoneblog.service.AdminServiceLayerInterface;
import com.sg.capstoneblog.service.BlogSiteServiceLayerInterface;
import com.sg.capstoneblog.service.HomePageServiceLayerInterface;
import com.sg.capstoneblog.service.StaticSitesServiceLayerInterface;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yingy
 */

@Controller
@RequestMapping("/site")
public class SiteController {
    
    HomePageServiceLayerInterface homeService;
    BlogSiteServiceLayerInterface blogService;
    StaticSitesServiceLayerInterface staticService;
    AdminServiceLayerInterface adminService;
    
    public SiteController(AdminServiceLayerInterface adminService,
            HomePageServiceLayerInterface homeService,
            BlogSiteServiceLayerInterface blogService,
            StaticSitesServiceLayerInterface staticService){
        this.blogService = blogService;
        this.homeService = homeService;
        this.staticService = staticService;
        this.adminService = adminService;
    }
    
}
