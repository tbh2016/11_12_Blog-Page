/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.controller;

import com.sg.capstoneblog.commanddto.BlogSite;
import com.sg.capstoneblog.commanddto.HomeSite;
import com.sg.capstoneblog.commanddto.MainBlog;
import com.sg.capstoneblog.commanddto.StaticSite;
import com.sg.capstoneblog.model.Category;
import com.sg.capstoneblog.model.Comment;
import com.sg.capstoneblog.model.HashTag;
import com.sg.capstoneblog.model.SiteData;
import com.sg.capstoneblog.model.User;
import com.sg.capstoneblog.service.AdminServiceLayerInterface;
import com.sg.capstoneblog.service.BlogSiteServiceLayerInterface;
import com.sg.capstoneblog.service.HomePageServiceLayerInterface;
import com.sg.capstoneblog.service.MainBlogSiteServiceLayerInterface;
import com.sg.capstoneblog.service.StaticSitesServiceLayerInterface;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author yingy
 */
@Controller
@RequestMapping({"/Page"})
public class TemplateController {

    HomePageServiceLayerInterface homeService;
    StaticSitesServiceLayerInterface staticService;
    BlogSiteServiceLayerInterface blogService;
    MainBlogSiteServiceLayerInterface mainBlogService;
    AdminServiceLayerInterface adminService;

    @Inject
    public TemplateController(HomePageServiceLayerInterface homeService,
            StaticSitesServiceLayerInterface staticService,
            BlogSiteServiceLayerInterface blogService,
            MainBlogSiteServiceLayerInterface mainBlogService,
            AdminServiceLayerInterface adminService) {
        this.homeService = homeService;
        this.staticService = staticService;
        this.blogService = blogService;
        this.mainBlogService = mainBlogService;
        this.adminService = adminService;
    }

    @RequestMapping("/searchHashTags")
    public String searchHashTags(HttpServletRequest request, Model model) {
        String searchTerm = request.getParameter("search");
        if (searchTerm.charAt(0) != '#') {
            searchTerm = '#' + searchTerm;
        }
        List<SiteData> sites = blogService.getAllBlogsForTag(searchTerm);
        MainBlog mainBlog = mainBlogService.getMainBlogSite();
        mainBlog.setBlogs(sites);
        if (sites.isEmpty()) {
            mainBlog.getSiteData().setTitle("No Results Found For: " + searchTerm);
        } else {
            mainBlog.getSiteData().setTitle("Results For: " + searchTerm);
        }
        model.addAttribute("mainBlogSite", mainBlog);

        return "mainBlog";
    }

    @RequestMapping(value = "/displayStaticPage", method = RequestMethod.GET)
    public String displayStaticPage(HttpServletRequest request, Model model) {
        int siteId = Integer.parseInt(request.getParameter("siteId"));
        StaticSite staticSite = staticService.getStaticSite(siteId);

        model.addAttribute("staticSite", staticSite);
        return "static";
    }

    @RequestMapping(value = "/displayHomePage", method = RequestMethod.GET)
    public String displayHomePage(Model model) {

        HomeSite homeSite = homeService.getHomePage();
        List<Category> categories = adminService.getAllCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("homeSite", homeSite);
        return "home";
    }

    /**
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/displayMainBlogPage", method = RequestMethod.GET)
    public String displayMainBlogPage(HttpServletRequest request, Model model) {

        List<Category> categories = adminService.getAllCategories();

        MainBlog mainBlog = mainBlogService.getMainBlogSite();
        model.addAttribute("mainBlogSite", mainBlog);
        model.addAttribute("categories", categories);

        return "mainBlog";
    }

    @RequestMapping(value = "/displayEditPage", method = RequestMethod.GET)
    public String displayEditPage(Map<String, Object> model) {

        model.put("message", "Hello from the controller");
        return "edit";
    }

    @RequestMapping(value = "/displayLoginPage", method = RequestMethod.GET)
    public String displayLoginPage(Model model) {

        HomeSite homeSite = homeService.getHomePage();

        model.addAttribute("homeSite", homeSite);

        return "login";
    }

    @RequestMapping(value = "/displayBlogPage", method = RequestMethod.GET)
    public String displayBlogPage(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("siteId"));

        BlogSite blogSite = blogService.getBlogSite(id);
        model.addAttribute("blogSite", blogSite);

        HomeSite homeSite = homeService.getHomePage();
        model.addAttribute("homeSite", homeSite);

        List<Comment> allComments = blogService.getAllCommentsBySiteId(id);
        List<Comment> commentShortList = new ArrayList<>();
        int commentCounter = 0;
        int commentsToShow = 3;
        if (commentCounter + commentsToShow >= allComments.size()) {
            commentsToShow = allComments.size() - commentCounter;
        }
        if (request.getParameter("commentCounter").equals("") || request.getParameter("commentCounter").equals(null)) {
            commentCounter = 0;
        } else {
            commentCounter = Integer.parseInt(request.getParameter("commentCounter"));
        }
        if (commentCounter >= allComments.size()) {
            commentCounter = 0;
        }
        for (int i = commentCounter; i < commentsToShow + commentCounter; i++) {
            if (i < allComments.size()) {
                commentShortList.add(allComments.get(i));
            }
        }
        commentCounter = commentCounter + commentsToShow;
        model.addAttribute("commentShortList", commentShortList);
        model.addAttribute("commentCounter", commentCounter);

        return "blog";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public String addComment(HttpServletRequest request) {
        String blogIdParameter = request.getParameter("siteDataId");
        int blogId = Integer.parseInt(blogIdParameter);
        Comment comment = new Comment();
        SiteData site = blogService.getSiteData(blogId);
        comment.setSite(site);
        comment.setTitle(request.getParameter("title"));
        comment.setContent(request.getParameter("content"));
        comment.setCommentDate(Date.valueOf(LocalDate.now()));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        //need to change user once security implemented
        User user = adminService.getUserByName(name);
        comment.setUser(user);
        blogService.addComment(comment);
        return "redirect:/Page/displayBlogPage?siteId=" + blogId + "&commentCounter=0";
    }

    @RequestMapping("/displayBlogsForHashTag")
    public String displayBlogsForHashTag(HttpServletRequest request, Model model) {
        int hashTagId = Integer.parseInt(request.getParameter("hashTagId"));

        List<SiteData> blogs = adminService.getBlogsForTag(hashTagId);
        MainBlog mainBlog = mainBlogService.getMainBlogSite();
        mainBlog.setBlogs(blogs);
        List<HashTag> tags = mainBlog.getHashTags();
        String hashTag = "";
        for (HashTag tag : tags) {
            if (tag.getHashTagId() == hashTagId) {
                hashTag = hashTag + tag.getHashTag();
            }
        }
        mainBlog.getSiteData().setTitle("Blogs With " + hashTag + " Hashtags");
        List<Category> categories = adminService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("mainBlogSite", mainBlog);

        return "mainBlog";

    }

    @RequestMapping("/displayBlogsForCategory")
    public String displayBlogsForCategory(HttpServletRequest request, Model model) {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Category category = adminService.getCategoryById(categoryId);

        List<SiteData> blogs = adminService.getBlogsForCategory(categoryId);
        MainBlog mainBlog = mainBlogService.getMainBlogSite();
        mainBlog.setBlogs(blogs);
        List<HashTag> tags = mainBlog.getHashTags();

        mainBlog.getSiteData().setTitle("Blogs In The " + category.getCategoryName() + " Category");
        List<Category> categories = adminService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("mainBlogSite", mainBlog);

        return "mainBlog";

    }

//    @RequestMapping(value = {"/getPicture"}, method = RequestMethod.GET)
//    public void getPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        Picture picture = adminService.getPictureById(Integer.parseInt(request.getParameter("pictureId")));
//
//        response.reset();
//        response.setContentType(".jpg");
//        response.setBufferSize(picture.getImage().length);
//        response.getOutputStream().write(picture.getImage());
//    }
}
