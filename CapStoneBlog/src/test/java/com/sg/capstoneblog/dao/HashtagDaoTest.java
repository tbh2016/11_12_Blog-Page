/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.HashTag;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class HashtagDaoTest {

    HashtagDao dao;

    public HashtagDaoTest() {
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
        dao = ctx.getBean("hashtagDao", HashtagDao.class);
        List<HashTag> tags = dao.getAllHashTags();
        for (HashTag tag : tags) {
            if (tag.getHashTagId() > 5) {
                dao.deleteHashTag(tag.getHashTagId());
            }
        }
    }

    @After
    public void tearDown() {
        List<HashTag> tags = dao.getAllHashTags();
        for (HashTag tag : tags) {
            if (tag.getHashTagId() > 5) {
                dao.deleteHashTag(tag.getHashTagId());
            }
        }
    }

    @Test
    public void testGetAll() {
        int size = dao.getAllHashTags().size();
        assertEquals(5, size);
    }

    @Test
    public void testAddDelete() {
        HashTag ht = dao.getHashTag(1);
        dao.addNewHashTag(ht);

        int size = dao.getAllHashTags().size();
        assertEquals(6, size);

        dao.deleteHashTag(ht.getHashTagId());
        size = dao.getAllHashTags().size();
        assertEquals(5, size);

    }

    @Test
    public void testUpdateTag() {
        HashTag ht = dao.getHashTag(1);
        dao.addNewHashTag(ht);
        ht.setHashTag("#nomoney");
        dao.updateHashTag(ht);
        HashTag newHT = dao.getHashTag(ht.getHashTagId());
        assertTrue("#nomoney".equals(newHT.getHashTag()));
        int size = dao.getAllHashTags().size();
        assertEquals(6, size);
        dao.deleteHashTag(newHT.getHashTagId());
    }

    @Test
    public void testGetTrending() {
        int size = dao.getTrendingHashTags(1).size();
        assertEquals(1, size);

        HashTag ht = dao.getTrendingHashTags(1).get(0);
        assertEquals(2, ht.getHashTagId());

        size = dao.getTrendingHashTags(3).size();
        assertEquals(3, size);
    }

    @Test
    public void testGetTagsForSite() {
        int size = dao.getTagsForSite(2).size();
        assertEquals(0, size);

        size = dao.getTagsForSite(5).size();
        assertEquals(3, size);

        size = dao.getTagsForSite(4).size();
        assertEquals(2, size);

    }

}
