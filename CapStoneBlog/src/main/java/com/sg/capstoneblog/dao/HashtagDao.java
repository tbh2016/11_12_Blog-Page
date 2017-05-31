/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.HashTag;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface HashtagDao {

    public HashTag addNewHashTag(HashTag ht);

    public HashTag getHashTag(int hashTagId);

    public HashTag getHashTagByName(String hashTag);

    public void updateHashTag(HashTag ht);

    public void deleteHashTag(int hashTagId);

    public List<HashTag> getAllHashTags();

    public List<HashTag> getTrendingHashTags(int numOfTags);

    public List<HashTag> getTagsForSite(int siteId);
}
