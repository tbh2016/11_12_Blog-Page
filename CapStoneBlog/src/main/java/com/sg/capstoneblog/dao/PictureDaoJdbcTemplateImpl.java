/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Picture;
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
public class PictureDaoJdbcTemplateImpl implements PictureDao {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SQL_INSERT_PICTURE  
            = "insert into Pictures (pictureData, pictureName) "
            + "values(?, ?)";
    
    private static final String SQL_DELETE_PICTURE 
            = "delete from Pictures where pictureId = ?";
    
    private static final String SQL_GET_ALL_PICTURES 
            = "select * from Pictures";
    
    private static final String SQL_GET_PICTURE_BY_ID 
            = "select * from Pictures where pictureId = ?";

      
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Picture addPicture(Picture picture) {
        jdbcTemplate.update(SQL_INSERT_PICTURE, 
                picture.getImage(),
                picture.getTitle());
        int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        picture.setPictureId(newId);
        return picture;
    }
    
    @Override
    public void deletePicture(int pictureId) {
        jdbcTemplate.update(SQL_DELETE_PICTURE, pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
        try {
        return jdbcTemplate.queryForObject(SQL_GET_PICTURE_BY_ID, 
                new PictureMapper(), 
                pictureId);
        } catch (EmptyResultDataAccessException ex){
         return null;   
        }
    }

    @Override
    public List<Picture> getAllPictures() {
        return jdbcTemplate.query(SQL_GET_ALL_PICTURES, new PictureMapper());
    }
    
    public static final class PictureMapper implements RowMapper<Picture>{

        @Override
        public Picture mapRow(ResultSet rs, int i) throws SQLException {
            Picture p = new Picture();
            p.setPictureId(rs.getInt("pictureId"));
            p.setImage(rs.getBytes("pictureData"));
            p.setTitle(rs.getString("pictureName"));
            return p;
        }
        
    }
    
}
