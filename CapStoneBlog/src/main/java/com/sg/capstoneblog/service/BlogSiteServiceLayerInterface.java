/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.BlogSite;
import com.sg.capstoneblog.model.Comment;
import com.sg.capstoneblog.model.SiteData;
import java.util.List;

/**
 *
 * @author cmcmi
 */
public interface BlogSiteServiceLayerInterface {

    public BlogSite getBlogSite(int siteDataId);

    public SiteData getSiteData(int siteDataId);

    public void updateSiteData(SiteData siteData);

    public void addComment(Comment comment);

    public void updateComment(Comment comment);

    public void deleteComment(int commentId);

    public Comment getCommentById(int commentId);
    
    public List <Comment> getAllCommentsBySiteId(int siteDataId);

    public List<SiteData> getAllBlogs();

    public List<SiteData> getAllBlogsForTag(String hashTag);

}
