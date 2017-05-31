/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Category;
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
 * @author cmcmi
 */
public class CategoryDaoTest {
    
    public CategoryDao dao;

    public CategoryDaoTest() {
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
        dao = ctx.getBean("categoryDao", CategoryDao.class);
        
        List<Category> categories = dao.getAllCategories();
        for(Category category : categories) {
            // greater than 5 bc there are 5 values entered for our test data
            if (category.getCategoryId() > 5) {
                dao.removeCategory(category.getCategoryId());
            }
        }
    }
    
    @After
    public void tearDown() {
        List<Category> categories = dao.getAllCategories();
        
        for(Category category : categories) {
        // greater than 5 bc there are 5 values entered for our test data 
            if(category.getCategoryId() > 5) {
                dao.removeCategory(category.getCategoryId());
            }
        }
    }

    @Test
    public void testAddGetRemoveGetAllCategory() {
        Category cat = new Category();
        cat.setCategoryName("Random");
        cat.setDescription("Just some description");
        cat = dao.addCategory(cat);
        int size = dao.getAllCategories().size();
        assertEquals(6, size);
        Category fromDb = dao.getCategoryById(cat.getCategoryId());
        assertEquals(fromDb, cat);
        dao.removeCategory(cat.getCategoryId());
        size = dao.getAllCategories().size();
        assertEquals(5, size);
        assertNull(dao.getCategoryById(cat.getCategoryId()));
    }

    @Test
    public void testUpdateContact() {
        Category cat = new Category();
        cat.setCategoryName("UTcat");
        cat.setDescription("blah blah cat cat");
        cat = dao.addCategory(cat);
        cat.setCategoryName("Checkers");
        cat.setDescription("cat cat works");
        dao.updateContact(cat);
        Category fromDb = dao.getCategoryById(cat.getCategoryId());
        assertEquals(fromDb, cat);
        
    }

}
