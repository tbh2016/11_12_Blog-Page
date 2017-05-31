/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.MainBlog;
import com.sg.capstoneblog.dao.CategoryDao;
import com.sg.capstoneblog.dao.ContactInfoDao;
import com.sg.capstoneblog.dao.HashtagDao;
import com.sg.capstoneblog.dao.LinkDao;
import com.sg.capstoneblog.dao.PictureDao;
import com.sg.capstoneblog.dao.SiteDataDao;
import com.sg.capstoneblog.dao.UserDao;
import com.sg.capstoneblog.model.Category;
import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.HashTag;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.SiteData;
import com.sg.capstoneblog.model.User;
import java.util.List;

/**
 *
 * @author yingy
 */
public class MainBlogSiteServiceLayer implements MainBlogSiteServiceLayerInterface {

    LinkDao linkDao;
    ContactInfoDao contactDao;
    SiteDataDao siteDataDao;
    PictureDao picDao;
    UserDao userDao;
    CategoryDao categoryDao;
    HashtagDao hashDao;

    private static final int NUM_OF_BLOGS = 15;
    private static final int NUM_OF_TAGS = 20;

    public MainBlogSiteServiceLayer(LinkDao linkDao, ContactInfoDao contactDao, HashtagDao hashDao,
            SiteDataDao siteDataDao, PictureDao picDao, UserDao userDao, CategoryDao categoryDao) {
        this.linkDao = linkDao;
        this.contactDao = contactDao;
        this.hashDao = hashDao;
        this.siteDataDao = siteDataDao;
        this.picDao = picDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public MainBlog getMainBlogSite() {
        MainBlog blog = new MainBlog();
        List<Link> mainLinks = linkDao.getMainSiteLinks();
        ContactInfo contact = contactDao.getContactInfo(1);
        List<SiteData> allBlogs = siteDataDao.getRecentBlogs(NUM_OF_BLOGS);
        List<HashTag> popularTags = hashDao.getTrendingHashTags(NUM_OF_TAGS);
        SiteData site = getSiteData();

        blog.setContact(contact);
        blog.setHashTags(popularTags);
        blog.setLinks(mainLinks);
        blog.setSiteData(site);
        blog.setBlogs(allBlogs);

        return blog;
    }

    private SiteData getSiteData() {
        SiteData site = siteDataDao.getBlogPage();
        User user = userDao.getUserById(site.getUser().getUserId());
        Category cat = null;
        if (site.getCategory() != null) {
            cat = categoryDao.getCategoryById(site.getCategory().getCategoryId());
        }

        site.setCategory(cat);
        site.setUser(user);

        return site;
    }

}
