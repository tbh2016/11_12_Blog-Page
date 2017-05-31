/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Link;
import java.util.List;
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
public class LinkDaoTest {

    LinkDao dao;

    public LinkDaoTest() {
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
        dao = ctx.getBean("linkDao", LinkDao.class);
        List<Link> links = dao.getAllLinks();
        for (Link link : links) {
            if (link.getLinkId() > 6) {
                dao.deleteLink(link.getLinkId());
            }
        }
    }

    @After
    public void tearDown() {
        List<Link> links = dao.getAllLinks();
        for (Link link : links) {
            if (link.getLinkId() > 6) {
                dao.deleteLink(link.getLinkId());
            }
        }
    }

    /**
     * Test of addLink method, of class LinkDao.
     */
    @Test
    public void testGetAll() {
        int size = dao.getAllLinks().size();
        assertEquals(6, size);
    }

    @Test
    public void testGetAddDel() {
        int size = dao.getAllLinks().size();
        assertEquals(6, size);

        Link link = dao.getLink(1);
        assertNotNull(link);

        dao.addLink(link);

        size = dao.getAllLinks().size();
        assertEquals(7, size);

        dao.deleteLink(link.getLinkId());

        size = dao.getAllLinks().size();
        assertEquals(6, size);

    }

    @Test
    public void testUpdateLink() {
        Link link = dao.getLink(1);
        link.setLinkName("TESTLINK");
        dao.addLink(link);
        dao.updateLink(link);

        int size = dao.getAllLinks().size();
        assertEquals(7, size);

        Link newLink = dao.getLink(link.getLinkId());

        assertTrue("TESTLINK".equals(newLink.getLinkName()));

    }

    @Test
    public void testGetLinkForSite() {
        Link link = dao.getLinkForSite(1);
        assertNotNull(link);

        Link link2 = dao.getLinkForSite(-1);
        assertNull(link2);

    }

}
