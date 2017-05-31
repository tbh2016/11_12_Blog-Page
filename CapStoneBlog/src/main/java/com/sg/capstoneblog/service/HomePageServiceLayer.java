/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.HomeSite;
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
import javax.inject.Inject;

/**
 *
 * @author yingy
 */
public class HomePageServiceLayer implements HomePageServiceLayerInterface {

    LinkDao linkDao;
    ContactInfoDao contactDao;
    SiteDataDao siteDataDao;
    PictureDao picDao;
    UserDao userDao;
    CategoryDao categoryDao;
    HashtagDao hashDao;

    //Todo: elsewhere ?
    private static final int NUM_OF_TAGS = 15;
    private static final int NUM_OF_BLOGS = 3;

    @Inject
    public HomePageServiceLayer(LinkDao linkDao, ContactInfoDao contactDao, HashtagDao hashDao,
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
    public HomeSite getHomePage() {
        HomeSite hs = new HomeSite();
        List<Link> links = linkDao.getMainSiteLinks();
        SiteData site = getSiteData();
        List<HashTag> tags = hashDao.getTrendingHashTags(NUM_OF_TAGS);
        //Todo: Fix this .... How to get proper contact info without using a magic number
        ContactInfo contactInfo = contactDao.getContactInfo(1);
        List<SiteData> blogs = siteDataDao.getRecentBlogs(NUM_OF_BLOGS);

        hs.setBlogList(blogs);
        hs.setContact(contactInfo);
        hs.setHashTags(tags);
        hs.setLinks(links);
        hs.setSiteData(site);

        return hs;
    }

    private SiteData getSiteData() {
        SiteData site = siteDataDao.getHomePage();
        User user = userDao.getUserById(site.getUser().getUserId());
        Category cat = null;
        if (site.getCategory() != null) {
            cat = categoryDao.getCategoryById(site.getCategory().getCategoryId());
        }

        site.setCategory(cat);
        site.setUser(user);

        return site;
    }

    @Override
    public void updateSiteData(SiteData siteData) {
        siteDataDao.updateSite(siteData);
    }

    @Override
    public SiteData getSiteData(int siteDataId) {
        SiteData siteData = siteDataDao.getSite(siteDataId);
        return siteData;
    }

}
