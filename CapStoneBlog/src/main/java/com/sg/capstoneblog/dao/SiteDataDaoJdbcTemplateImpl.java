/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Category;
import com.sg.capstoneblog.model.SiteData;
import com.sg.capstoneblog.model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cmcmi
 */
public class SiteDataDaoJdbcTemplateImpl implements SiteDataDao {

    private JdbcTemplate jdbcTemplate;
    private static final int HOME_TYPE_ID = 1;
    private static final int STATIC_PAGE_TYPE_ID = 2;
    private static final int BLOG_HOME_TYPE_ID = 3;
    private static final int BLOG_ENTRY_TYPE_ID = 4;
    private static final int ADMIN_PAGE_TYPE_ID = 5;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_GET_SITE
            = "select title, content, publishedDate, startDate, endDate, siteId, pageTypeId, userId, categoryId from SiteData "
            + " where siteId = ?";

    private static final String SQL_ADD_SITE
            = "insert into SiteData (title, content, publishedDate, startDate, endDate, pageTypeId, userId, categoryId)"
            + " values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_SITE
            = "delete from SiteData where siteId = ?";

    private static final String SQL_GET_ALL_SITES
            = "select * from SiteData";

    private static final String SQL_UPDATE_SITE
            = "update SiteData set title = ?, content = ?, publishedDate = ?, startDate = ?, "
            + " endDate = ?, pageTypeId = ?, userId = ?, categoryId = ? where siteId = ?";
    private static final String SQL_GET_RECENT_BLOGS
            = "select * from SiteData where pageTypeId = ? order by publishedDate limit ?;";

    private static final String SQL_GET_BLOGS_BY_HASH_TAG
            = "select sd.* from SiteData sd "
            + " inner join SiteHashTagBridge shtb "
            + " on sd.siteId = shtb.siteId "
            + "where shtb.hashTagId= ?";

    private static final String SQL_GET_BLOGS_BY_CATEGORY
            = "select * from SiteData where categoryId = ?";

    private static final String SQL_GET_ALL_PAGES
            = "select * from SiteData where pageTypeId = ?";

    private static final String SQL_GET_HOME_PAGE
            = "select * from SiteData where pageTypeId = ?";
    private static final String SQL_GET_BLOG_MAIN_PAGE
            = "select * from SiteData where pageTypeId = ?";

    @Override
    public SiteData getSite(int siteId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_SITE, new SiteDataMapper(), siteId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public SiteData addSite(SiteData site) {
        //Numerous checks for nulls, ensure data is going in as I expect
        jdbcTemplate.update(SQL_ADD_SITE, site.getTitle(),
                site.getContent(),
                site.getPublishDate() == null ? null : site.getPublishDate(),
                site.getStartDate() == null ? null : site.getStartDate(),
                site.getEndDate() == null ? null : site.getEndDate(),
                site.getPageType(),
                site.getUser().getUserId(),
                site.getCategory() == null ? null : site.getCategory().getCategoryId());

        site.setSiteDataId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return site;
    }

    @Override
    public void updateSite(SiteData site) {
        jdbcTemplate.update(SQL_UPDATE_SITE,
                site.getTitle(),
                site.getContent(),
                site.getPublishDate() == null ? null : site.getPublishDate(),
                site.getStartDate() == null ? null : site.getStartDate(),
                site.getEndDate() == null ? null : site.getEndDate(),
                site.getPageType(),
                site.getUser().getUserId(),
                site.getCategory() == null ? null : site.getCategory().getCategoryId(),
                site.getSiteDataId()
        );
    }

    @Override
    public void deleteSite(int siteId) {
        jdbcTemplate.update(SQL_DELETE_SITE, siteId);
    }

    @Override
    public List<SiteData> getRecentBlogs(int numberOfBlogs) {
        return jdbcTemplate.query(SQL_GET_RECENT_BLOGS, new SiteDataMapper(),
                BLOG_ENTRY_TYPE_ID, numberOfBlogs);
    }

    @Override
    public List<SiteData> getSitesByHashTag(int hashTagId) {
        return jdbcTemplate.query(SQL_GET_BLOGS_BY_HASH_TAG, new SiteDataMapper(), hashTagId);
    }

    @Override
    public List<SiteData> getSitesByCategory(int categoryId) {
        return jdbcTemplate.query(SQL_GET_BLOGS_BY_CATEGORY, new SiteDataMapper(), categoryId);
    }

    @Override
    public List<SiteData> getAllSites() {
        return jdbcTemplate.query(SQL_GET_ALL_SITES, new SiteDataMapper());
    }

    @Override
    public SiteData getHomePage() {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_HOME_PAGE, new SiteDataMapper(), HOME_TYPE_ID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public SiteData getBlogPage() {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_BLOG_MAIN_PAGE, new SiteDataMapper(), BLOG_HOME_TYPE_ID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<SiteData> getAllBlogs() {
        return jdbcTemplate.query(SQL_GET_ALL_PAGES, new SiteDataMapper(), BLOG_ENTRY_TYPE_ID);
    }

    @Override
    public List<SiteData> getAllStaticSites() {
        return jdbcTemplate.query(SQL_GET_ALL_PAGES, new SiteDataMapper(), STATIC_PAGE_TYPE_ID);
    }

    private class SiteDataMapper implements RowMapper<SiteData> {

        @Override
        public SiteData mapRow(ResultSet rs, int i) throws SQLException {
            SiteData sd = new SiteData();

            sd.setContent(rs.getString("content"));
            sd.setTitle(rs.getString("title"));
            sd.setPageType(rs.getInt("pageTypeId"));
            sd.setSiteDataId(rs.getInt("siteId"));

            User user = new User();
            user.setUserId(rs.getInt("userId"));
            sd.setUser(user);

            //Checks for null values coming back. If so, sets the category object or dates to null -- need to have LOTS of checks in place
            Category cat = new Category();
            cat.setCategoryId(rs.getInt("categoryId"));
            sd.setCategory(rs.getInt("categoryId") == 0 ? null : cat);

            Timestamp time = rs.getTimestamp("publishedDate");
            sd.setPublishDate(time != null ? rs.getDate("publishedDate") : null);

            time = rs.getTimestamp("startDate");
            sd.setStartDate(time != null ? rs.getDate("startDate") : null);

            time = rs.getTimestamp("endDate");
            sd.setEndDate(time != null ? rs.getDate("startDate") : null);

            return sd;
        }

    }

}
