/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.SiteData;
import java.util.List;
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
public class SiteDataDaoTest {

    SiteDataDao dao;

    public SiteDataDaoTest() {
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
        dao = ctx.getBean("siteDataDao", SiteDataDao.class);
        List<SiteData> sites = dao.getAllSites();
        for (SiteData site : sites) {
            if (site.getSiteDataId() > 6) {
                dao.deleteSite(site.getSiteDataId());
            }
        }
    }

    @After
    public void tearDown() {
        List<SiteData> sites = dao.getAllSites();
        for (SiteData site : sites) {
            if (site.getSiteDataId() > 6) {
                dao.deleteSite(site.getSiteDataId());
            }
        }
    }

    @Test
    public void testGet() {
        SiteData sd = dao.getSite(1);
        assertNotNull(sd);
    }

    @Test
    public void testAddDeleteGetAll() {
        int size = dao.getAllSites().size();
        assertEquals(6, size);
        SiteData sd = dao.getSite(1);
        sd = dao.addSite(sd);
        assertNotNull(sd);
        size = dao.getAllSites().size();
        assertEquals(7, size);
        dao.deleteSite(sd.getSiteDataId());
        size = dao.getAllSites().size();
        assertEquals(6, size);

    }

    @Test
    public void testUpdateDelete() {
        SiteData sd = dao.getSite(1);
        sd = dao.addSite(sd);
        assertTrue(sd.getSiteDataId() > 5);
        sd.setContent("NEWCONTENT");
        dao.updateSite(sd);
        SiteData newSd = dao.getSite(sd.getSiteDataId());
        assertTrue("NEWCONTENT".equals(newSd.getContent()));
        assertEquals(newSd.getSiteDataId(), sd.getSiteDataId());
    }

    @Test
    public void testGetRecentBlogs() {
        int size = dao.getRecentBlogs(2).size();
        //SHOULD BE 3, but with test data only two blogs exist
        assertEquals(2, size);
    }

    @Test
    public void testGetAllByTags() {
        //1 = #blessed = 1 entry
        //2 = #99poblems = 2 entries
        int size = dao.getSitesByHashTag(1).size();
        assertEquals(1, size);

        size = dao.getSitesByHashTag(2).size();
        assertEquals(2, size);

        size = dao.getSitesByHashTag(-1).size();
        assertEquals(0, size);
    }

}
