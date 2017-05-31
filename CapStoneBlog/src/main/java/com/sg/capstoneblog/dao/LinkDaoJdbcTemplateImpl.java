/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Link;
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
public class LinkDaoJdbcTemplateImpl implements LinkDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String SQL_ADD_LINK
            = "insert into Links (linkName, linkReference, isMainLink, siteId, positioning) values (?, ?, ?, ?, ?)";

    private static final String SQL_GET_ALL_LINKS
            = "select * from Links";

    private static final String SQL_DELETE_LINK
            = "delete from Links where linkId = ?";

    private static final String SQL_GET_LINK = "select * from Links where linkId = ?";

    private static final String SQL_UPDATE_LINK
            = "update Links set linkName = ?, linkReference = ?, isMainLink = ?, siteId = ?, positioning = ? where linkId = ?";

    private static final String SQL_GET_MAIN_LINKS
            = "select * from Links where isMainLink = true order by positioning";

    private static final String SQL_GET_LINK_FOR_SITE
            = "select * from Links where siteId = ?";

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Link addLink(Link link) {
        jdbcTemplate.update(SQL_ADD_LINK,
                link.getLinkName(),
                link.getLinkReference(),
                link.isMainLink(),
                link.getSiteId(),
                link.getPosition()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        link.setLinkId(id);

        return link;
    }

    @Override
    public Link getLink(int linkId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_LINK, new LinkMapper(), linkId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteLink(int linkId) {
        jdbcTemplate.update(SQL_DELETE_LINK, linkId);
    }

    @Override
    public Link updateLink(Link link) {
        jdbcTemplate.update(SQL_UPDATE_LINK,
                link.getLinkName(),
                link.getLinkReference(),
                link.isMainLink(),
                link.getSiteId(),
                link.getPosition(),
                link.getLinkId()
        );
        return link;
    }

    @Override
    public List<Link> getAllLinks() {
        return jdbcTemplate.query(SQL_GET_ALL_LINKS, new LinkMapper());
    }

    @Override
    public List<Link> getMainSiteLinks() {
        return jdbcTemplate.query(SQL_GET_MAIN_LINKS, new LinkMapper());
    }

    @Override
    public Link getLinkForSite(int siteId) {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_LINK_FOR_SITE, new LinkMapper(), siteId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private class LinkMapper implements RowMapper<Link> {

        @Override
        public Link mapRow(ResultSet rs, int i) throws SQLException {
            Link link = new Link();
            link.setLinkId(rs.getInt("linkId"));
            link.setLinkName(rs.getString("linkName"));
            link.setLinkReference(rs.getString("linkReference"));
            link.setMainLink(rs.getBoolean("isMainLink"));
            link.setPosition(rs.getInt("positioning") == 0 ? null : rs.getInt("positioning"));
            link.setSiteId(rs.getInt("siteId"));
            return link;

        }

    }

}
