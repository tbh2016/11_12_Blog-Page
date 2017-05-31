/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Comment;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cmcmi
 */
public class CommentDaoTest {
    
    CommentDao dao;
    
    public CommentDaoTest() {
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
        dao = ctx.getBean("commentDao", CommentDao.class);
        
        List<Comment> comments = dao.getAllComments();
        for(Comment comment : comments) {
            if(comment.getCommentId() > 5) {
                dao.removeComment(comment.getCommentId());
            }
        }
    }

    @After
    public void tearDown() {
        List<Comment> comments = dao.getAllComments();
        for (Comment comment : comments) {
            if (comment.getCommentId() > 5) {
                dao.removeComment(comment.getCommentId());
            }
        }
    }

    @Test
    public void testAddGetRemoveGetAllComment() {
        int size = dao.getAllComments().size();
        assertEquals(5, size);
        Comment com = dao.getCommentById(1);
        com = dao.addComment(com);
        assertNotNull(com);
        size = dao.getAllComments().size();
        assertEquals(6, size);
        dao.removeComment(com.getCommentId());
        size = dao.getAllComments().size();
        assertEquals(5, size);
    }

    @Test
    public void testUpdateComment() {
        Comment com = dao.getCommentById(1);
        com = dao.addComment(com);
        assertTrue(com.getCommentId() > 5);
        com.setTitle("Timmmberrrrr");
        dao.updateComment(com);
        Comment newCom = dao.getCommentById(com.getCommentId());
        assertTrue("Timmmberrrrr".equals(newCom.getTitle()));
        assertEquals(newCom.getCommentId(), com.getCommentId());
    }

}
