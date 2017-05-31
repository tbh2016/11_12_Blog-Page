/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.BlogSite;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cmcmi
 */
public class BlogSiteServiceLayerTest {

    BlogSiteServiceLayer service;

    public BlogSiteServiceLayerTest() {
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
        service = ctx.getBean("blogSiteService", BlogSiteServiceLayer.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getBlogSite method, of class BlogSiteServiceLayer.
     */
    @Test
    public void testGetBlogSite() {

        BlogSite blogSite = service.getBlogSite(5);

        assertNotNull(blogSite);

        assertTrue("Kitties !!!!!".equals(blogSite.getSiteData().getTitle()));

        assertTrue("Out walking I saw all these kitties !!!".equals(blogSite.getSiteData().getContent()));

        assertEquals(4, blogSite.getHashTags().size());

        assertTrue("401 S. Main".equals(blogSite.getContact().getAddress()));

        assertEquals(2, blogSite.getComments().size());

    }

    @Test
    public void testGetSitesByTag() {
        int size = service.getAllBlogsForTag("#blessed").size();
        assertEquals(1, size);

        size = service.getAllBlogsForTag("#99poblems").size();
        assertEquals(2, size);

    }

}
