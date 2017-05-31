/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Picture;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class PictureDaoTest {
    
    PictureDao dao;
    
    public PictureDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("pictureDao", PictureDao.class);
    }
    
    @After
    public void tearDown() {
        List<Picture> picsutersToDelete = dao.getAllPictures();
        for(Picture p : picsutersToDelete){
            dao.deletePicture(p.getPictureId());
        }
    }

    /**
     * Test of addPicture method, of class PictureDao.
     */
    @Test
    public void testAddPicture() {
        byte[] testArray = new byte[] {12, 12, 12, 88, -90};
        Picture p = new Picture();
        p.setTitle("test picture");
        p.setImage(testArray);
        p = dao.addPicture(p);
        assertEquals(p, dao.getPictureById(p.getPictureId()));
    }

    /**
     * Test of deletePicture method, of class PictureDao.
     */
    @Test
    public void testDeletePicture() {
        int id;
        byte[] testArray = new byte[] {12, 12, 12, 88, -90};
        Picture p = new Picture();
        p.setTitle("test picture");
        p.setImage(testArray);
        p = dao.addPicture(p);
        
        assertEquals(p, dao.getPictureById(p.getPictureId()));
        
        id = p.getPictureId();
        dao.deletePicture(id);
        
        assertNull(dao.getPictureById(id));
    }

    /**
     * Test of getPictureById method, of class PictureDao.
     */
    @Test
    public void testGetPictureById() {
        byte[] testArray1 = new byte[] {12, 12, 12, 88, -90};
        Picture p1 = new Picture();
        p1.setTitle("test picture");
        p1.setImage(testArray1);
        p1 = dao.addPicture(p1);
        
        byte[] testArray2 = new byte[] {1, 2, 12, 80, -90};
        Picture p2 = new Picture();
        p2.setTitle("test picture");
        p2.setImage(testArray2);
        p2 = dao.addPicture(p2);
        
        byte[] testArray3 = new byte[] {12, 3, -90};
        Picture p3 = new Picture();
        p3.setTitle("test picture");
        p3.setImage(testArray3);
        p3 = dao.addPicture(p3);
        
        byte[] testArray4 = new byte[] {10, -20, -44, 1};
        Picture p4 = new Picture();
        p4.setTitle("test picture");
        p4.setImage(testArray4);
        p4 = dao.addPicture(p4);
        
        assertEquals(4, dao.getAllPictures().size());
        
        assertEquals(p2, dao.getPictureById(p2.getPictureId()));
        
        assertEquals(p4, dao.getPictureById(p4.getPictureId()));
    }
    
}
