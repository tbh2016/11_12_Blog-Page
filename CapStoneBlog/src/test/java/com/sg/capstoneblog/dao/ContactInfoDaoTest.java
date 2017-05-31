/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.ContactInfo;
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
public class ContactInfoDaoTest {

    public ContactInfoDao dao;

    public ContactInfoDaoTest() {
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
        dao = ctx.getBean("contactInfoDao", ContactInfoDao.class);
        List<ContactInfo> contacts = dao.getAllContactInfo();
        //Greater than 1 because ONLY 1 contact info allowed (probably)
        for (ContactInfo contact : contacts) {
            if (contact.getContactInfoId() > 1) {
                dao.deleteContactInfo(contact.getContactInfoId());
            }
        }
    }

    @After
    public void tearDown() {
        List<ContactInfo> contacts = dao.getAllContactInfo();
        //Greater than 1 because ONLY 1 contact info allowed (probably)
        for (ContactInfo contact : contacts) {
            if (contact.getContactInfoId() > 1) {
                dao.deleteContactInfo(contact.getContactInfoId());
            }
        }
    }

    @Test
    public void testGetAll() {
        int size = dao.getAllContactInfo().size();
        assertEquals(1, size);

    }

    @Test
    public void testGetAddDelete() {
        //1 for the test DB
        ContactInfo c = dao.getContactInfo(1);
        assertNotNull(c);
        int size = dao.getAllContactInfo().size();
        assertEquals(1, size);

        ContactInfo newContact = dao.addContactInfo(c);
        size = dao.getAllContactInfo().size();
        assertEquals(2, size);

        dao.deleteContactInfo(newContact.getContactInfoId());
        size = dao.getAllContactInfo().size();
        assertEquals(1, size);

    }

    @Test
    public void testUpdate() {
        ContactInfo c = dao.getContactInfo(1);
        ContactInfo newContact = dao.addContactInfo(c);
        newContact.setAddress("STREET");
        dao.updateContactInfo(newContact);

        ContactInfo testC = dao.getContactInfo(newContact.getContactInfoId());
        assertTrue("STREET".equals(testC.getAddress()));

        dao.deleteContactInfo(testC.getContactInfoId());

    }

}
