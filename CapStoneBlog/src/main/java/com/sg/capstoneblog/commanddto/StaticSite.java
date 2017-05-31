/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.commanddto;

import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.Picture;
import com.sg.capstoneblog.model.SiteData;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author yingy
 */
public class StaticSite {
    private List<Link> links;
    private SiteData siteData;
    private List<Picture> pictures;
    private ContactInfo contact;

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public SiteData getSiteData() {
        return siteData;
    }

    public void setSiteData(SiteData siteData) {
        this.siteData = siteData;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.links);
        hash = 97 * hash + Objects.hashCode(this.siteData);
        hash = 97 * hash + Objects.hashCode(this.pictures);
        hash = 97 * hash + Objects.hashCode(this.contact);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaticSite other = (StaticSite) obj;
        if (!Objects.equals(this.links, other.links)) {
            return false;
        }
        if (!Objects.equals(this.siteData, other.siteData)) {
            return false;
        }
        if (!Objects.equals(this.pictures, other.pictures)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return true;
    }

    
    
}
