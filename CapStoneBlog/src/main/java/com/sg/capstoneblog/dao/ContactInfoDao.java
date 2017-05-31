/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.ContactInfo;
import java.util.List;

/**
 *
 * @author chris
 */
public interface ContactInfoDao {

    public ContactInfo addContactInfo(ContactInfo contact);

    public void updateContactInfo(ContactInfo contact);

    public ContactInfo getContactInfo(int contactId);

    public void deleteContactInfo(int contactId);

    public List<ContactInfo> getAllContactInfo();
}
