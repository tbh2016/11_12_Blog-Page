/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.StaticSite;
import com.sg.capstoneblog.dao.ContactInfoDao;
import com.sg.capstoneblog.dao.LinkDao;
import com.sg.capstoneblog.dao.PictureDao;
import com.sg.capstoneblog.dao.SiteDataDao;
import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.SiteData;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author yingy
 */
public class StaticSitesServiceLayer implements StaticSitesServiceLayerInterface{
    LinkDao linkDao;
    ContactInfoDao contactDao;
    SiteDataDao siteDataDao;
    PictureDao picDao;
    
    @Inject
    public StaticSitesServiceLayer(LinkDao linkDao, ContactInfoDao contactDao, 
                                   SiteDataDao siteDataDao, PictureDao picDao){
        this.linkDao = linkDao;
        this.contactDao = contactDao;
        this.siteDataDao = siteDataDao;
        this.picDao = picDao;
    }

    @Override
    public StaticSite getStaticSite(int siteId) {
        StaticSite ss = new StaticSite();
        ContactInfo contact  = contactDao.getContactInfo(1);
        List<Link> links = linkDao.getMainSiteLinks();
        SiteData site = siteDataDao.getSite(siteId);
        
        ss.setContact(contact);
        ss.setLinks(links);
        ss.setSiteData(site);
        
        return ss;
    }

}
