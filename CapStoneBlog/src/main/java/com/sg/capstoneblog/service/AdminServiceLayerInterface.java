/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.model.Category;
import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.HashTag;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.Picture;
import com.sg.capstoneblog.model.SiteData;
import com.sg.capstoneblog.model.User;
import java.util.List;

/**
 *
 * @author chris
 */
public interface AdminServiceLayerInterface {

    public void updateHomePage(SiteData site);

    public void updateBlogHomePage(SiteData site);

    public SiteData addNewBlog(SiteData site);

    public Category addCategory(Category category);

    public void updateBlogSite(SiteData site);

    public void deleteBlogSite(int siteId);

    public void deleteCategory(int categoryId);

    public SiteData addNewStaticSite(SiteData site);

    public void updateStaticSite(SiteData site);

    public void deleteStaticSite(int siteId);

    public void updateContactInfo(ContactInfo contact);

    public ContactInfo getContactInfo();

    public Picture addPicture(Picture picture);

    public void deletePicture(int pictureId);

    public Picture getPictureById(int pictureId);

    public List<Picture> getAllPictures();

    public List<Category> getAllCategories();

    public Category getCategoryById(int categoryId);

    public User getUserByName(String userName);

    public User getUserById(int userId);

    public List<SiteData> getAllBlogs();

    public List<SiteData> getAllStaticPages();

    public Link addNewLink(Link link);

    public void deleteLink(int linkId);

    public Link updateLink(Link link);

    public HashTag addNewHashTags(HashTag ht);

    public List<HashTag> getHashTags(String content);

    public void addConnection(int tagId, int blogId);

    public void deleteConnection(int tagId, int blogId);

    public List<SiteData> getBlogsForTag(int tagId);

    public List<HashTag> getTagsforBlog(int blogId);

    public List<SiteData> getBlogsForCategory(int categoryId);
}
