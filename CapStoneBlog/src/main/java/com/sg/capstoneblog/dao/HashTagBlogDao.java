/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import java.util.List;

/**
 *
 * @author chris
 */
public interface HashTagBlogDao {

    public void addTagBlog(int hashTagId, int blogId);

    public void deleteTagBLog(int hashTagId, int blogId);

    public List<Integer> getTagsForBlog(int blogId);

    public List<Integer> getBlogDataForTags(int hashTagId);

    public List<Integer> findSitesForHashTag(String hashTag);

}
