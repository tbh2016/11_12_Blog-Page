/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.model.HashTag;
import java.util.List;
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
public class AdminServiceLayerInterfaceTest {

    AdminServiceLayer service;

    public AdminServiceLayerInterfaceTest() {
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
        service = ctx.getBean("adminService", AdminServiceLayer.class);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of updateHomePage method, of class AdminServiceLayerInterface.
     */
    @Test
    public void testGetHashTags() {
        String content = "'<p>Here is a test !!! WO WO WO&nbsp;</p>\n"
                + "<p>&nbsp;</p>#taghere#andhere #one #1 ut nooo "
                + "<p style=\"text-align: center;\">Now Im In th mdills e !</p>\n"
                + "<p style=\"text-align: center;\">&nbsp;</p>\n"
                + "<p style=\"text-align: center;\">Text goes here<img src=\"/CapStoneBlog/admin/getPicture?pictureId=2\" width=\"135\" height=\"132\" />and text here</p>'";
        List<HashTag> tags = service.getHashTags(content);
        assertEquals(3, tags.size());
    }

}
