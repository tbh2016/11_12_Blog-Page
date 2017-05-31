/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.MainBlog;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author chris
 */
public class MainBlogSiteServiceLayerTest {

    MainBlogSiteServiceLayer service;

    public MainBlogSiteServiceLayerTest() {
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
        service = ctx.getBean("mainBlogService", MainBlogSiteServiceLayer.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMainBlogSite method, of class MainBlogSiteServiceLayer.
     */
    @Test
    public void testGetMainBlogSite() {
        MainBlog blog = service.getMainBlogSite();

        assertNotNull(blog);

        assertTrue("All My blogs".equals(blog.getSiteData().getTitle()));

        assertEquals(4, blog.getHashTags().size());

        assertTrue("Thuan".equals(blog.getSiteData().getUser().getFirstName()));

        assertNull(blog.getSiteData().getCategory());

    }

}
