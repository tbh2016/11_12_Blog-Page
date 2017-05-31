/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.dao.LinkDao;
import com.sg.capstoneblog.model.Link;
import java.util.List;

/**
 *
 * @author chris
 */
public class LinkService implements LinkServiceInterface {

    LinkDao linkDao;

    public LinkService(LinkDao linkDao) {
        this.linkDao = linkDao;
    }

    @Override
    public List<Link> getMainLinks() {
        List<Link> links = linkDao.getMainSiteLinks();
        return links;
    }

    @Override
    public Link getLinkBySiteDataId(int siteDataId) {
       return linkDao.getLinkForSite(siteDataId);
    }

    @Override
    public Link updateLink(Link link) {
        return linkDao.updateLink(link);
    }

}
