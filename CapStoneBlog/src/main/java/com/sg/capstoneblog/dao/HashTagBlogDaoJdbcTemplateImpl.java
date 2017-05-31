/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author chris
 */
public class HashTagBlogDaoJdbcTemplateImpl implements HashTagBlogDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_CONNECTION
            = "insert into SiteHashTagBridge values (?, ?)";

    private static final String SQL_DELETE_CONNECTION
            = "delete from SiteHashTagBridge where siteId = ? and hashTagId = ?";

    private static final String SQL_GET_TAGS_FOR_SITE
            = "select hashTagId from SiteHashTagBridge where siteId = ?";

    private static final String SQL_GET_SITES_FOR_TAG
            = "select siteId from SiteHashTagBridge where hashTagId = ?";

    private static final String SQL_SEARCH_TAGS
            = "select shtb.siteId from SiteHashTagBridge shtb"
            + " inner join HashTags ht on ht.hashTagId = shtb.hashTagId "
            + " where ht.hashTag = ?";

    @Override
    public void addTagBlog(int hashTagId, int blogId) {
        jdbcTemplate.update(SQL_ADD_CONNECTION, blogId, hashTagId);
    }

    @Override
    public void deleteTagBLog(int hashTagId, int blogId) {
        jdbcTemplate.update(SQL_DELETE_CONNECTION, blogId, hashTagId);
    }

    @Override
    public List<Integer> getTagsForBlog(int blogId) {
        return jdbcTemplate.queryForList(SQL_GET_TAGS_FOR_SITE, Integer.class, blogId);
    }

    @Override
    public List<Integer> getBlogDataForTags(int hashTagId) {
        return jdbcTemplate.queryForList(SQL_GET_SITES_FOR_TAG, Integer.class, hashTagId);
    }

    @Override
    public List<Integer> findSitesForHashTag(String hashTag) {
        return jdbcTemplate.queryForList(SQL_SEARCH_TAGS, Integer.class, hashTag);
    }

}
