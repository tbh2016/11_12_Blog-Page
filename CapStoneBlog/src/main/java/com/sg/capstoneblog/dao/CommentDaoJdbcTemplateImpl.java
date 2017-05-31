/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Comment;
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
public class CommentDaoJdbcTemplateImpl implements CommentDao {

    private static final String SQL_GET_COMMENT
            = "select commentId, siteId, userName, title, content, commentDate from Comments "
            + "where commentId = ?";

    private static final String SQL_INSERT_COMMENT
            = "insert into Comments "
            + "(siteId, userName, title, content, commentDate) "
            + "values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_COMMENT
            = "delete from Comments where commentId = ?";

    private static final String SQL_SELECT_COMMENT
            = "select * from Comments where commentId = ?";

    private static final String SQL_UPDATE_COMMENT
            = "update Comments set "
            + "siteId = ?, userName = ?, title = ?, content = ?, commentDate = ? "
            + "where commentId = ?";

    private static final String SQL_SELECT_ALL_COMMENTS
            = "select * from Comments";

    private static final String SQL_SELECT_ALL_COMMENTS_FOR_SITE
            = "select * from Comments "
            + "where siteId = ? "
            + "order by commentDate, commentId desc";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Comment addComment(Comment comment) {

        jdbcTemplate.update(SQL_INSERT_COMMENT,
                comment.getSite().getSiteDataId(),
                comment.getUser().getUserName(),
                comment.getTitle(),
                comment.getContent() == null ? null : comment.getContent(),
                comment.getCommentDate() == null ? null : comment.getCommentDate());

        comment.setCommentId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        return comment;
    }

    @Override
    public void removeComment(int commentId) {
        jdbcTemplate.update(SQL_DELETE_COMMENT,
                commentId);
    }

    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update(SQL_UPDATE_COMMENT,
                comment.getSite().getSiteDataId(),
                comment.getUser().getUserId(),
                comment.getTitle(),
                comment.getContent(),
                comment.getCommentDate() == null ? null : comment.getCommentDate(),
                comment.getCommentId());
    }

    @Override
    public List<Comment> getAllComments() {
        return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS, new CommentMapper());
    }

    @Override
    public Comment getCommentById(int commentId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_COMMENT, new CommentMapper(), commentId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Comment> getAllCommentsForSite(int siteId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_COMMENTS_FOR_SITE, new CommentMapper(), siteId);
    }

    private class CommentMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(ResultSet rs, int i) throws SQLException {
            Comment com = new Comment();

            com.setTitle(rs.getString("title"));
            com.setContent(rs.getString("content"));
            com.setCommentId(rs.getInt("commentId"));

            Timestamp time = rs.getTimestamp("commentDate");
            com.setCommentDate(time != null ? rs.getDate("commentDate") : null);

            User user = new User();
            user.setUserName(rs.getString("userName"));
            com.setUser(user);

            SiteData sd = new SiteData();
            sd.setSiteDataId(rs.getInt("siteId"));
            com.setSite(sd);

            return com;
        }
    }
}
