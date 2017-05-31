/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class HashTagBlogDaoTest {

    HashTagBlogDao dao;

    public HashTagBlogDaoTest() {
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
        dao = ctx.getBean("tagBlogBridge", HashTagBlogDao.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        dao.addTagBlog(1, 1);
    }

    @Test
    public void testDelete() {
        dao.deleteTagBLog(1, 1);
    }

    @Test
    public void testGetSites() {
        int size = dao.getBlogDataForTags(2).size();

        assertEquals(2, size);
    }

    @Test
    public void testGetTags() {
        int size = dao.getTagsForBlog(5).size();

        assertEquals(3, size);
        dao.addTagBlog(1, 5);

        size = dao.getTagsForBlog(5).size();

        assertEquals(4, size);

        dao.deleteTagBLog(1, 5);

    }

    @Test
    public void testGetSiteIdByTag() {
        int size = dao.findSitesForHashTag("#blessed").size();
        assertEquals(1, size);

        size = dao.findSitesForHashTag("#99poblems").size();
        assertEquals(2, size);
    }

}
