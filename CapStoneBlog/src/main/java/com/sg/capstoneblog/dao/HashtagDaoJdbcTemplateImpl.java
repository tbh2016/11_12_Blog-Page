/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.HashTag;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class HashtagDaoJdbcTemplateImpl implements HashtagDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_GET_ALL_TAGS
            = "select * from HashTags";

    private static final String SQL_ADD_TAG
            = "insert into HashTags (hashTag) values (?)";

    private static final String SQL_DELETE_TAG
            = "delete from HashTags where hashTagId = ?";

    private static final String SQL_GET_TAG
            = "select * from HashTags where hashTagId = ?";

    private static final String SQL_GET_TAG_BY_NAME
            = "select * from HashTags where hashTag = ?";

    private static final String SQL_UPDATE_TAG
            = "update HashTags set hashTag = ? where hashTagId = ?";

    private static final String SQL_GET_TRENDING_TAGS
            = " select ht.* from HashTags ht "
            + " inner join SiteHashTagBridge b on b.hashTagId = ht.hashTagId "
            + " group by b.hashTagId "
            + " order by Count(b.hashTagId) desc "
            + " limit ?";

    private static final String SQL_GET_TAGS_FOR_SITE
            = "select ht.* from HashTags ht inner join SiteHashTagBridge b "
            + " on ht.hashTagId = b.hashTagId where b.siteId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public HashTag addNewHashTag(HashTag ht) {
        jdbcTemplate.update(SQL_ADD_TAG, ht.getHashTag());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        ht.setHashTagId(id);

        return ht;
    }

    @Override
    public HashTag getHashTag(int hashTagId) {
        return jdbcTemplate.queryForObject(SQL_GET_TAG, new HashTagMapper(), hashTagId);
    }

    @Override
    public void updateHashTag(HashTag ht) {
        jdbcTemplate.update(SQL_UPDATE_TAG, ht.getHashTag(), ht.getHashTagId());
    }

    @Override
    public void deleteHashTag(int hashTagId) {
        jdbcTemplate.update(SQL_DELETE_TAG, hashTagId);
    }

    @Override
    public List<HashTag> getAllHashTags() {
        return jdbcTemplate.query(SQL_GET_ALL_TAGS, new HashTagMapper());
    }

    @Override
    public List<HashTag> getTrendingHashTags(int numOfTags) {
        return jdbcTemplate.query(SQL_GET_TRENDING_TAGS, new HashTagMapper(), numOfTags);
    }

    @Override
    public List<HashTag> getTagsForSite(int siteId) {
        return jdbcTemplate.query(SQL_GET_TAGS_FOR_SITE, new HashTagMapper(), siteId);
    }

    @Override
    public HashTag getHashTagByName(String hashTag) {
        try {
            HashTag ht = jdbcTemplate.queryForObject(SQL_GET_TAG_BY_NAME, new HashTagMapper(), hashTag);
            return ht;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static class HashTagMapper implements RowMapper<HashTag> {

        @Override
        public HashTag mapRow(ResultSet rs, int i) throws SQLException {
            HashTag ht = new HashTag();

            ht.setHashTag(rs.getString("hashTag"));
            ht.setHashTagId(rs.getInt("hashTagId"));

            return ht;

        }

    }

}
