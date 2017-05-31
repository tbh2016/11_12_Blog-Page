/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.BlogSite;
import com.sg.capstoneblog.dao.CategoryDao;
import com.sg.capstoneblog.dao.CommentDao;
import com.sg.capstoneblog.dao.ContactInfoDao;
import com.sg.capstoneblog.dao.HashTagBlogDao;
import com.sg.capstoneblog.dao.HashtagDao;
import com.sg.capstoneblog.dao.LinkDao;
import com.sg.capstoneblog.dao.PictureDao;
import com.sg.capstoneblog.dao.SiteDataDao;
import com.sg.capstoneblog.dao.UserDao;
import com.sg.capstoneblog.model.Category;
import com.sg.capstoneblog.model.Comment;
import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.HashTag;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.SiteData;
import com.sg.capstoneblog.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yingy
 */
public class BlogSiteServiceLayer implements BlogSiteServiceLayerInterface {

    LinkDao linkDao; // need links for the top nav bar page
    ContactInfoDao contactDao; // for footer info
    SiteDataDao siteDataDao; // wil need the content and info for the page
    PictureDao picDao; // need to get up to 3 images for the blog post
    UserDao userDao; // wll need to know who the user is accessing the page
    CategoryDao categoryDao; // will need category(ies) of the blog post
    HashtagDao hashDao; // need 20 popular hashtags
    CommentDao commentDao; // will need comments for the blog post
    HashTagBlogDao hashTagBlogDao;

    private static final int NUM_OF_TAGS = 20;

    public BlogSiteServiceLayer(LinkDao linkDao, ContactInfoDao contactDao,
            HashtagDao hashDao, SiteDataDao siteDataDao, PictureDao picDao,
            UserDao userDao, CategoryDao categoryDao, CommentDao commentDao,
            HashTagBlogDao hashTagBlogDao) {
        this.linkDao = linkDao;
        this.contactDao = contactDao;
        this.hashDao = hashDao;
        this.siteDataDao = siteDataDao;
        this.picDao = picDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.commentDao = commentDao;
        this.hashTagBlogDao = hashTagBlogDao;
    }

    @Override
    public BlogSite getBlogSite(int siteDataId) {
        BlogSite blogSite = new BlogSite();
        List<Link> blogSiteLinks = linkDao.getMainSiteLinks();
        ContactInfo contact = contactDao.getContactInfo(1);
        List<HashTag> popularTags = hashDao.getTrendingHashTags(NUM_OF_TAGS);
//        List<Picture> picutres = null; // Need implement once PictureDao is finished
        List<Comment> comments = commentDao.getAllCommentsForSite(siteDataId);
        SiteData blogSiteData = getSiteData(siteDataId);

        blogSite.setLinks(blogSiteLinks);
        blogSite.setSiteData(blogSiteData);
//        blogSite.setPictures(picutres);
        blogSite.setContact(contact);
        blogSite.setHashTags(popularTags);
        blogSite.setComments(comments);
        blogSite.setCategory(blogSiteData.getCategory());

        return blogSite;
    }

    @Override
    public SiteData getSiteData(int siteDataId) {
        SiteData site = siteDataDao.getSite(siteDataId);
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
    public List<SiteData> getAllBlogs() {
        List<SiteData> blogSiteData = siteDataDao.getAllBlogs();
        return blogSiteData;
    }

    @Override
    public void updateSiteData(SiteData siteData) {
        siteDataDao.updateSite(siteData);
    }

    @Override
    public void addComment(Comment comment) {
        commentDao.addComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        commentDao.updateComment(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commentDao.removeComment(commentId);
    }

    @Override
    public Comment getCommentById(int commentId) {
        Comment comment = commentDao.getCommentById(commentId);
        return comment;
    }

    @Override
    public List<SiteData> getAllBlogsForTag(String hashTag) {
        List<Integer> siteIds;
        siteIds = hashTagBlogDao.findSitesForHashTag(hashTag);
        List<SiteData> sites = new ArrayList<>();
        for (Integer siteId : siteIds) {
            SiteData site = siteDataDao.getSite(siteId);
            sites.add(site);
        }
        return sites;
    }

    @Override
    public List<Comment> getAllCommentsBySiteId(int siteDataId) {
        List<Comment> comments = commentDao.getAllCommentsForSite(siteDataId);
        return comments;
    }

}
