/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Comment;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface CommentDao {
    
    public Comment addComment(Comment comment);
    
    public void removeComment(int commentId);
    
    public void updateComment(Comment comment);
    
    public List<Comment> getAllComments();
    
    public Comment getCommentById(int commentId);
    
    public List<Comment> getAllCommentsForSite(int siteId);
    
}
