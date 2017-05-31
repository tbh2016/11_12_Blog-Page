/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.controller;

import com.sg.capstoneblog.commanddto.BlogSite;
import com.sg.capstoneblog.model.Category;
import com.sg.capstoneblog.model.Comment;
import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.HashTag;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.Picture;
import com.sg.capstoneblog.model.Results;
import com.sg.capstoneblog.model.SiteData;
import com.sg.capstoneblog.model.User;
import com.sg.capstoneblog.service.AdminServiceLayerInterface;
import com.sg.capstoneblog.service.BlogSiteServiceLayerInterface;
import com.sg.capstoneblog.service.HomePageServiceLayerInterface;
import com.sg.capstoneblog.service.LinkServiceInterface;
import com.sg.capstoneblog.service.StaticSitesServiceLayerInterface;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author yingy
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final int HOME_PAGE_TYPE = 1;
    private static final int STATIC_PAGE_TYPE = 2;
    private static final int BLOG_HOME_PAGE_TYPE = 3;
    private static final int BLOG_PAGE_TYPE = 4;
    private static final int ADMIN_PAGE_TYPE = 5;

    AdminServiceLayerInterface adminService;
    HomePageServiceLayerInterface homeService;
    BlogSiteServiceLayerInterface blogService;
    StaticSitesServiceLayerInterface staticService;
    LinkServiceInterface linkService;

    @Inject
    AdminController(AdminServiceLayerInterface adminService,
            HomePageServiceLayerInterface homeService,
            BlogSiteServiceLayerInterface blogService,
            StaticSitesServiceLayerInterface staticService,
            LinkServiceInterface linkService) {
        this.blogService = blogService;
        this.adminService = adminService;
        this.homeService = homeService;
        this.staticService = staticService;
        this.linkService = linkService;
    }

    @RequestMapping
    public String showAdminPage(Model model) {
        ContactInfo contact = adminService.getContactInfo();

        model.addAttribute("contact", contact);
        return "admin";
    }

    @RequestMapping("/editHomePage")
    public String showAdminHome(Model model) {
        SiteData siteData = homeService.getHomePage().getSiteData();
        model.addAttribute("siteData", siteData);
        return "editHomePage";
    }

    @RequestMapping("/updateHomePage")
    public String updateHomePage(HttpServletRequest request,
            @ModelAttribute("siteData") SiteData siteData) {
        SiteData originalData = homeService.getSiteData(siteData.getSiteDataId());
        originalData.setTitle(siteData.getTitle());
        originalData.setContent(siteData.getContent());
        homeService.updateSiteData(originalData);
        return "redirect:/admin";
    }

    @RequestMapping("/editContactInfo")
    public String showEditContactInfo(HttpServletRequest request, Model model) {
        ContactInfo contact = adminService.getContactInfo();

        model.addAttribute("contact", contact);

        return "editContactInfo";

    }

    @RequestMapping("/updateContact")
    public String updateContact(@ModelAttribute("contact") ContactInfo contact) {
        adminService.updateContactInfo(contact);
        return "redirect:/admin";
    }

    @RequestMapping("/staticPages")
    public String adminStaticPages(Model model) {
        List<SiteData> staticPages = adminService.getAllStaticPages();
        model.addAttribute("staticPages", staticPages);
        return "adminStatic";
    }

    @RequestMapping("/blogPages")
    public String adminBlogPages(Model model) {
        List<SiteData> blogPages = blogService.getAllBlogs();
        model.addAttribute("blogPages", blogPages);
        return "adminBlog";
    }

    @RequestMapping("/adminCategories")
    public String dminCategories(Model model) {
        List<Category> categories = adminService.getAllCategories();
        model.addAttribute("categories", categories);
        return "adminCategories";
    }

    @RequestMapping(value = "/displayEditBlogPage", method = RequestMethod.GET)
    public String displayEditBlogPage(HttpServletRequest request, Model model) {
        String blogSiteIdParameter = request.getParameter("siteDataId");
        int blogSiteId = Integer.parseInt(blogSiteIdParameter);
        BlogSite blogSite = blogService.getBlogSite(blogSiteId);
        List<Category> categories = adminService.getAllCategories();

        model.addAttribute("blogSite", blogSite);
        model.addAttribute("categories", categories);

        return "adminEditBlogPage";
    }
    
    //todo resolve loss of siteData for blogSite between sf form and controller

    @RequestMapping(value = "/updateBlogPage", method = RequestMethod.POST)
    public String updateBlogPage(HttpServletRequest request,
            @ModelAttribute("blogSite") BlogSite blogSite, BindingResult result) {
        
        SiteData updatedSiteData = blogSite.getSiteData();
        blogService.updateSiteData(updatedSiteData);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/displayEditStaticPage", method = RequestMethod.GET)
    public String displayEditStaticPage(HttpServletRequest request, Model model) {
        String staticSiteIdParameter = request.getParameter("siteDataId");
        int pageDataId = Integer.parseInt(staticSiteIdParameter);
        SiteData pageData = blogService.getSiteData(pageDataId);

        model.addAttribute("pageData", pageData);

        Link pageLink = linkService.getLinkBySiteDataId(pageDataId);

        model.addAttribute("link", pageLink);

        return "adminEditStaticPage";
    }

    @RequestMapping("/updateStaticPage")
    public String updateStaticPage(HttpServletRequest request,
            @ModelAttribute("pageData") SiteData updatedPageData, BindingResult result) {

        blogService.updateSiteData(updatedPageData);

        return "redirect:/admin";

    }

    @RequestMapping(value = "/deleteStaticPage", method = RequestMethod.GET)
    public String deleteStaticPage(HttpServletRequest request) {
        String staticPageIdParameter = request.getParameter("siteDataId");
        int staticSiteId = Integer.parseInt(staticPageIdParameter);
        adminService.deleteStaticSite(staticSiteId);

        return "redirect:/admin/staticPages";
    }

    @RequestMapping(value = "/addPicture", method = RequestMethod.POST)
    public String addPicture(HttpServletRequest request,
            Model model,
            @RequestParam("userfile") MultipartFile pictureFile) throws IOException {

        Picture picture = new Picture();
        byte[] image = pictureFile.getBytes();
        picture.setTitle("test boop");
        picture.setImage(image);
        picture = adminService.addPicture(picture);
        Results result = new Results();
        result.setResultCode("ok");
        result.setFileName("getPicture?pictureId=" + picture.getPictureId());
        result.setResult("file_uploaded");
        model.addAttribute("result", result);
        return "results";

    }

    @RequestMapping(value = "/addBlogPost", method = RequestMethod.POST)
    public String addBlogPost(HttpServletRequest request, Model model) throws IOException {
        SiteData blogPost = new SiteData();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = adminService.getUserByName(name);

        blogPost.setContent(request.getParameter("blog-post"));
        blogPost.setPageType(BLOG_PAGE_TYPE);
        blogPost.setTitle(request.getParameter("blog-title"));
        blogPost.setStartDate(null);
        blogPost.setEndDate(null);
        blogPost.setPublishDate(Date.valueOf(LocalDate.now()));
        blogPost.setUser(user);
        blogPost.setCategory(adminService.getCategoryById(Integer.parseInt(request.getParameter("category"))));

        blogPost = adminService.addNewBlog(blogPost);

        Link link = new Link();
        link.setLinkName(blogPost.getTitle());
        link.setLinkReference("/blogs/" + blogPost.getTitle().toLowerCase());
        link.setMainLink(false);
        link.setPosition(null);
        link.setSiteId(blogPost.getSiteDataId());
        adminService.addNewLink(link);

        List<HashTag> hashTags = adminService.getHashTags(blogPost.getContent());

        for (HashTag hashTag : hashTags) {
            HashTag newTag = adminService.addNewHashTags(hashTag);
            adminService.addConnection(newTag.getHashTagId(), blogPost.getSiteDataId());
        }

        return "redirect:/admin/blogPages";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(HttpServletRequest request, Model model) throws IOException {
        Category category = new Category();
        category.setCategoryName(request.getParameter("categoryName"));
        category.setDescription(request.getParameter("description"));
        adminService.addCategory(category);
        return "redirect:/admin/adminCategories";
    }

    @RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
    public String deleteCategory(HttpServletRequest request) {
        String categoryIdParameter = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParameter);
        adminService.deleteCategory(categoryId);

        return "redirect:/admin/adminCategories";
    }

    @RequestMapping(value = "/deleteBlogPost", method = RequestMethod.GET)
    public String deleteBlogPost(HttpServletRequest request) {
        String blogSiteIdParameter = request.getParameter("siteDataId");
        int blogSiteId = Integer.parseInt(blogSiteIdParameter);
        adminService.deleteBlogSite(blogSiteId);
        
        return "redirect:/admin/blogPages";
    }

    @RequestMapping("/deleteComment")
    public String deleteComment(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("commentId"));
        blogService.deleteComment(id);
        return "adminAddStaticPage";
    }

    @RequestMapping("/displayAddStaticPage")
    public String displayAddStaticPage(Model model) {
        List<Link> navLinks = homeService.getHomePage().getLinks();
        model.addAttribute("navLinks", navLinks);
        return "adminAddStaticPage";
    }

    @RequestMapping("/addStaticPage")
    public String addStaticPage(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = adminService.getUserByName(name);
        SiteData staticPage = new SiteData();

        staticPage.setPageType(STATIC_PAGE_TYPE);
        staticPage.setContent(request.getParameter("static-content"));
        staticPage.setTitle(request.getParameter("static-title"));
        staticPage.setPublishDate(Date.valueOf(LocalDate.now()));
        staticPage.setStartDate(null);
        staticPage.setEndDate(null);
        staticPage.setUser(user);//todo set user field base on publisher
        staticPage = adminService.addNewStaticSite(staticPage);

        List<Link> linksToOrder = homeService.getHomePage().getLinks();

        int newLinkPositon = Integer.parseInt(request.getParameter("nav-position"));

        Link linkToNewPage = new Link();
        linkToNewPage.setLinkName(request.getParameter("static-name"));
        linkToNewPage.setLinkReference("/Page/displayStaticPage");
        linkToNewPage.setLinkName(request.getParameter("static-link-name"));
        linkToNewPage.setMainLink(true);
        linkToNewPage.setPosition(newLinkPositon);
        linkToNewPage.setSiteId(staticPage.getSiteDataId());

        linkToNewPage = adminService.addNewLink(linkToNewPage);

        linksToOrder.add(newLinkPositon, linkToNewPage);
        int newPosition = 1;
        for (Link l : linksToOrder) {
            l.setPosition(newPosition);
            adminService.updateLink(l);
            newPosition++;
        }

        return "/admin";
    }

    @RequestMapping("/displayAddBlogPost")
    public String displayAddBlogPost(Model model) {
        model.addAttribute("catergories", adminService.getAllCategories());
        return "adminAddBlogPost";
    }

    @RequestMapping(value = {"/getPicture"}, method = RequestMethod.GET)
    public void getPicture(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Picture picture = adminService.getPictureById(Integer.parseInt(request.getParameter("pictureId")));

        response.reset();
        response.setContentType(".jpg");
        response.setBufferSize(picture.getImage().length);
        response.getOutputStream().write(picture.getImage());
    }

    @RequestMapping(value = "/displayEditCommentPage", method = RequestMethod.GET)
    public String displayEditCommentPage(HttpServletRequest request, Model model) {
        String commentIdParameter = request.getParameter("commentId");
        int commentId = Integer.parseInt(commentIdParameter);
        Comment comment = blogService.getCommentById(commentId);

        model.addAttribute("comment", comment);

        return "editCommentPage";

    }

    @RequestMapping("/updateComment")
    public String updateComment(HttpServletRequest request,
            @ModelAttribute("comment") Comment comment) {
        Comment originalComment = blogService.getCommentById(comment.getCommentId());
        originalComment.setTitle(comment.getTitle());
        originalComment.setContent(comment.getContent());
        blogService.updateComment(originalComment);
        return "redirect:/admin/blogPages";
    }

}
