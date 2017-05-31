/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.ContactInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cmcmi
 */
public class ContactInfoDaoJdbcTemplateImpl implements ContactInfoDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_CONTACT_INFO
            = "insert into ContactInfo (address, contactEmail, contactPhone) values (?, ?, ?)";

    private static final String SQL_GET_ALL_CONTACTS
            = "select * from ContactInfo";

    private static final String SQL_DELETE_CONTACT_INFO
            = "delete from ContactInfo where contactInfoId = ?";

    private static final String SQL_GET_CONTACT_INFO
            = "select * from ContactInfo where contactInfoId = ?";

    private static final String SQL_UPDATE_CONTACT_INFO
            = "update ContactInfo set address = ?, contactEmail = ?, contactPhone = ? where contactInfoId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public ContactInfo addContactInfo(ContactInfo contact) {
        jdbcTemplate.update(SQL_ADD_CONTACT_INFO,
                contact.getAddress(),
                contact.getContactEmail(),
                contact.getContactPhone()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        contact.setContactInfoId(id);
        return contact;

    }

    @Override
    public void updateContactInfo(ContactInfo contact) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT_INFO,
                contact.getAddress(),
                contact.getContactEmail(),
                contact.getContactPhone(),
                contact.getContactInfoId()
        );
    }

    @Override
    public ContactInfo getContactInfo(int contactId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_CONTACT_INFO, new ContactInfoMapper(), contactId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteContactInfo(int contactId) {
        jdbcTemplate.update(SQL_DELETE_CONTACT_INFO, contactId);
    }

    @Override
    public List<ContactInfo> getAllContactInfo() {
        return jdbcTemplate.query(SQL_GET_ALL_CONTACTS, new ContactInfoMapper());
    }

    private class ContactInfoMapper implements RowMapper<ContactInfo> {

        @Override
        public ContactInfo mapRow(ResultSet rs, int i) throws SQLException {
            ContactInfo c = new ContactInfo();
            c.setAddress(rs.getString("address"));
            c.setContactEmail(rs.getString("contactEmail"));
            c.setContactPhone(rs.getString("contactPhone"));
            c.setContactInfoId(rs.getInt("contactInfoId"));
            return c;
        }

    }
}
