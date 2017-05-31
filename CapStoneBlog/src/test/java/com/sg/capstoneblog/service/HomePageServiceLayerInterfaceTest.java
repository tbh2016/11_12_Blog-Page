/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.HomeSite;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class HomePageServiceLayerInterfaceTest {

    HomePageServiceLayer service;

    public HomePageServiceLayerInterfaceTest() {
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
        service = ctx.getBean("homeService", HomePageServiceLayer.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getHomePage method, of class StaticSiteServiceLayerInterface.
     */
    @Test
    public void testGetHomePage() {
        HomeSite hs = service.getHomePage();
        assertNotNull(hs);

        //TEST DB ONLY HAS TWO BLOGS
        assertEquals(2, hs.getBlogList().size());

        //TEST DB HAS 5 TAGS TOTAL, 4 BEING UTILIZED
        assertEquals(4, hs.getHashTags().size());

        assertEquals(3, hs.getLinks().size());

        assertTrue("401 S. Main".equals(hs.getContact().getAddress()));

        assertTrue("Home".equals(hs.getSiteData().getTitle()));
    }

}
