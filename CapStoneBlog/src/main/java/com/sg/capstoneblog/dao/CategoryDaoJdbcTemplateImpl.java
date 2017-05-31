/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Category;
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
public class CategoryDaoJdbcTemplateImpl implements CategoryDao {

    private static final String SQL_INSERT_CATEGORY
            = "insert into Category "
            + "(categoryName, categoryDescription) "
            + "values (?, ?)";

    private static final String SQL_DELETE_CATEGORY
            = "delete from Category where categoryId = ?";

    private static final String SQL_SELECT_CATEGORY
            = "select * from Category where categoryId = ?";

    private static final String SQL_UPDATE_CATEGORY
            = "update Category set "
            + "categoryName = ?, categoryDescription = ? "
            + "where categoryId = ?";

    private static final String SQL_SELECT_ALL_CATEGORIES
            = "select * from Category";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Category addCategory(Category category) {
        jdbcTemplate.update(SQL_INSERT_CATEGORY,
                category.getCategoryName(),
                category.getDescription());

        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        category.setCategoryId(newId);

        return category;
    }

    @Override
    public void removeCategory(int categoryId) {
        jdbcTemplate.update(SQL_DELETE_CATEGORY, categoryId);
    }

    @Override
    public void updateContact(Category category) {
        jdbcTemplate.update(SQL_UPDATE_CATEGORY,
                category.getCategoryName(),
                category.getDescription(),
                category.getCategoryId());
    }

    @Override
    public List<Category> getAllCategories() {
        return jdbcTemplate.query(SQL_SELECT_ALL_CATEGORIES,
                new CategoryMapper());
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_CATEGORY,
                    new CategoryMapper(), categoryId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    // CategoryMapper
    private static final class CategoryMapper implements RowMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {
            Category category = new Category();

            category.setCategoryId(rs.getInt("categoryId"));
            category.setCategoryName(rs.getString("categoryName"));
            category.setDescription(rs.getString("categoryDescription"));

            return category;
        }
    }

}
