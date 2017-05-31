/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

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
import com.sg.capstoneblog.model.Picture;
import com.sg.capstoneblog.model.SiteData;
import com.sg.capstoneblog.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;

/**
 *
 * @author chris
 */
public class AdminServiceLayer implements AdminServiceLayerInterface {

    LinkDao linkDao;
    ContactInfoDao contactDao;
    SiteDataDao siteDataDao;
    PictureDao picDao;
    UserDao userDao;
    CategoryDao categoryDao;
    HashtagDao hashDao;
    PictureDao pictureDao;
    CommentDao commentDao;
    HashTagBlogDao hashTagBridgeDao;

    @Inject
    public AdminServiceLayer(LinkDao linkDao, ContactInfoDao contactDao, HashtagDao hashDao,
            SiteDataDao siteDataDao, PictureDao picDao, UserDao userDao, CategoryDao categoryDao,
            PictureDao pictureDao, CommentDao commentDao, HashTagBlogDao htbDao) {

        this.linkDao = linkDao;
        this.contactDao = contactDao;
        this.hashDao = hashDao;
        this.siteDataDao = siteDataDao;
        this.picDao = picDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.pictureDao = pictureDao;
        this.commentDao = commentDao;
        this.hashTagBridgeDao = htbDao;
    }

    @Override
    public void updateHomePage(SiteData site) {
        siteDataDao.updateSite(site);
    }

    @Override
    public void updateBlogHomePage(SiteData site) {
        siteDataDao.updateSite(site);
    }

    @Override
    public SiteData addNewBlog(SiteData site) {
        return siteDataDao.addSite(site);

    }

    @Override
    public void updateBlogSite(SiteData site) {
        siteDataDao.updateSite(site);
    }

    @Override
    public void deleteBlogSite(int siteId) {
        List<Comment> commentsToDelete = commentDao.getAllCommentsForSite(siteId);
        for (Comment c : commentsToDelete) {
            commentDao.removeComment(c.getCommentId());
        }
        List<HashTag> hashTagsToDelete = hashDao.getTagsForSite(siteId);
        for (HashTag ht : hashTagsToDelete) {
            deleteConnection(ht.getHashTagId(), siteId);
        }
        linkDao.deleteLink(siteId);
        siteDataDao.deleteSite(siteId);
    }

    @Override
    public SiteData addNewStaticSite(SiteData site) {
        return siteDataDao.addSite(site);
    }

    @Override
    public void updateStaticSite(SiteData site) {
        siteDataDao.updateSite(site);
    }

    @Override
    public void deleteStaticSite(int siteId) {

        Link link = linkDao.getLinkForSite(siteId);
        linkDao.deleteLink(link.getLinkId());
        siteDataDao.deleteSite(siteId);
    }

    @Override
    public void updateContactInfo(ContactInfo contact) {
        contactDao.updateContactInfo(contact);
    }

    @Override
    public ContactInfo getContactInfo() {
        //Todo: NEED TO CHANGE -- DOES NOT TAKE A NUMBER
        return contactDao.getContactInfo(1);
    }

    @Override
    public Picture addPicture(Picture picture) {
        return pictureDao.addPicture(picture);
    }

    @Override
    public void deletePicture(int pictureId) {
        pictureDao.deletePicture(pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
        return pictureDao.getPictureById(pictureId);
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureDao.getAllPictures();
    }

    public List<SiteData> getAllBlogs() {
        return siteDataDao.getAllBlogs();
    }

    @Override
    public List<SiteData> getAllStaticPages() {
        return siteDataDao.getAllStaticSites();

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryDao.getCategoryById(categoryId);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public Link addNewLink(Link link) {
        return linkDao.addLink(link);
    }

    @Override
    public HashTag addNewHashTags(HashTag ht) {
        HashTag newTag = new HashTag();
        newTag = hashDao.getHashTagByName(ht.getHashTag());
        if (newTag == null) {
            newTag = hashDao.addNewHashTag(ht);
        }
        return newTag;

    }

    @Override
    public void addConnection(int tagId, int blogId) {
        hashTagBridgeDao.addTagBlog(tagId, blogId);
    }

    @Override
    public void deleteConnection(int tagId, int blogId) {
        hashTagBridgeDao.deleteTagBLog(tagId, blogId);
    }

    @Override
    public List<SiteData> getBlogsForTag(int tagId) {
        List<Integer> blogIds = hashTagBridgeDao.getBlogDataForTags(tagId);
        List<SiteData> sites = new ArrayList<>();
        for (Integer blogId : blogIds) {

            SiteData site = siteDataDao.getSite(blogId);
            User user = userDao.getUserById(site.getUser().getUserId());
            Category cat = null;
            if (site.getCategory() != null) {
                cat = categoryDao.getCategoryById(site.getCategory().getCategoryId());
            }

            site.setCategory(cat);
            site.setUser(user);

            sites.add(site);

        }
        return sites;

    }

    @Override
    public List<SiteData> getBlogsForCategory(int categoryId) {
        return siteDataDao.getSitesByCategory(categoryId);
    }

    @Override
    public List<HashTag> getTagsforBlog(int blogId) {
        List<Integer> tagIds = hashTagBridgeDao.getTagsForBlog(blogId);
        List<HashTag> tags = new ArrayList<>();

        for (Integer tagId : tagIds) {
            HashTag tag = hashDao.getHashTag(tagId);
            tags.add(tag);

        }

        return tags;
    }

    @Override
    public List<HashTag> getHashTags(String content) {
        //Todo: WORK ON HASHTAGS REGEX

        List<String> tagString = new ArrayList<>();
        Matcher m = Pattern.compile("[#]++([A-Za-z])+([A-Za-z0-9-_]+)")
                .matcher(content);
        while (m.find()) {
            tagString.add(m.group());
        }
//        String[] splitString = content.matches("#([^#1-9]+)[\\s,;]*");
//        for (String string : splitString) {
//            if (string.isEmpty() == false && string.charAt(0) == '#' && string.length() > 1) {
//                tagString.add(string);
//            }
//        }

        List<HashTag> tags = new ArrayList<>();
        for (String string : tagString) {
            HashTag newTag = new HashTag();
            newTag.setHashTag(string);
            tags.add(newTag);
        }

        return tags;
    }

    @Override
    public Category addCategory(Category category) {
        categoryDao.addCategory(category);
        return category;
    }

    @Override
    public void deleteCategory(int categoryId) {
        categoryDao.removeCategory(categoryId);
    }

    public void deleteLink(int linkId) {
        linkDao.deleteLink(linkId);
    }

    @Override
    public Link updateLink(Link link) {
        return linkDao.updateLink(link);
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

}
