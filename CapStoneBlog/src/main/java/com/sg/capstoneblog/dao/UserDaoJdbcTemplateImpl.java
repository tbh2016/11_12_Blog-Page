/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.User;
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
public class UserDaoJdbcTemplateImpl implements UserDao {

    private static final String SQL_INSERT_USER
            = "insert into Users "
            + "(userName, userFirstName, userLastName, password) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_USER
            = "delete from Users where userName = ?";

    private static final String SQL_SELECT_USER_BY_NAME
            = "select * from Users where userName= ?";

    private static final String SQL_SELECT_USER_BY_ID
            = "select * from Users where userId= ?";

    private static final String SQL_UPDATE_USER
            = "update Users set "
            + "userName = ?, userFirstName = ?, userLastName = ?, password = ? "
            + "where userId = ?";

    private static final String SQL_SELECT_ALL_USERS
            = "select * from Users where isActive = true";

    private static final String SQL_INSERT_AUTHORITY
            = "insert into Authorities (userName, authority) values (?, ?)";

    private static final String SQL_DELETE_AUTHORITY
            = "delete from Authorities where userName = ?";

    private static final String SQL_DEACTIVATE_USER
            = "update Users set isActive = false where userId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER,
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword());

        user.setUserId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        List<String> authorities = user.getAuthorities();
        for (String authority : authorities) {
            jdbcTemplate.update(SQL_INSERT_AUTHORITY, user.getUserName(), authority);
        }

        return user;
    }

    @Override
    public void removeUser(String userName) {
        jdbcTemplate.update(SQL_DELETE_AUTHORITY, userName);
        jdbcTemplate.update(SQL_DELETE_USER, userName);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_USERS, new UserMapper());
    }

    @Override
    public User getUserByName(String userName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_NAME, new UserMapper(), userName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User getUserById(int userId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, new UserMapper(), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deactivateUser(int userId) {
        jdbcTemplate.update(SQL_DEACTIVATE_USER, userId);
    }

    private class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("userId"));
            user.setUserName(rs.getString("userName"));
            user.setFirstName(rs.getString("userFirstName"));
            user.setLastName(rs.getString("userLastName"));
            user.setPassword(rs.getString("password"));

            return user;
        }
    }

}
