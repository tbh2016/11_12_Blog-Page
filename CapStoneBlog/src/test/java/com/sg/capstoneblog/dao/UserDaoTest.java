/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.User;
import java.util.List;
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
public class UserDaoTest {

    UserDao dao;

    public UserDaoTest() {
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
        dao = ctx.getBean("userDao", UserDao.class);

        List<User> users = dao.getAllUsers();
        for (User user : users) {
            if (user.getUserId() > 5) {
                dao.removeUser(user.getUserName());
            }
        }
    }

    @After
    public void tearDown() {
        List<User> users = dao.getAllUsers();
        for (User user : users) {
            if (user.getUserId() > 5) {
                dao.removeUser(user.getUserName());
            }
        }
    }

    @Test
    public void testAddGetRemoveGetAllUser() {
        int size = dao.getAllUsers().size();
        assertEquals(5, size);
        User user = dao.getUserByName("admin");
        user.setUserName("NEWCHRIS");
        user = dao.addUser(user);
        assertNotNull(user);
        size = dao.getAllUsers().size();
        assertEquals(6, size);
        dao.removeUser(user.getUserName());
        size = dao.getAllUsers().size();
        assertEquals(5, size);
    }

}
