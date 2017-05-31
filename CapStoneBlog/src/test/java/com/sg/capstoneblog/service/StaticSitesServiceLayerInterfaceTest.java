/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.StaticSite;
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
 * @author jayce
 */
public class StaticSitesServiceLayerInterfaceTest {

    StaticSitesServiceLayer service;

    public StaticSitesServiceLayerInterfaceTest() {
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
        service = ctx.getBean("staticSitesService", StaticSitesServiceLayer.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getStaticSite method, of class StaticSitesServiceLayerInterface.
     */
    @Test
    public void testGetStaticSite() {

        StaticSite ss = service.getStaticSite(3);
        assertNotNull(ss);

        assertEquals(3, ss.getLinks().size());

        assertTrue("401 S. Main".equals(ss.getContact().getAddress()));

        assertTrue("About Us".equals(ss.getSiteData().getTitle()));

    }

}
