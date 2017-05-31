/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.commanddto;

import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.HashTag;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.SiteData;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author yingy
 */
public class MainBlog {

    private List<Link> links;
    private SiteData siteData;
    private ContactInfo contact;
    private List<HashTag> hashTags;
    private List<SiteData> blogs;

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

    public ContactInfo getContact() {
        return contact;
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public List<SiteData> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<SiteData> blogs) {
        this.blogs = blogs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.links);
        hash = 53 * hash + Objects.hashCode(this.siteData);
        hash = 53 * hash + Objects.hashCode(this.contact);
        hash = 53 * hash + Objects.hashCode(this.hashTags);
        hash = 53 * hash + Objects.hashCode(this.blogs);
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
        final MainBlog other = (MainBlog) obj;
        if (!Objects.equals(this.links, other.links)) {
            return false;
        }
        if (!Objects.equals(this.siteData, other.siteData)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.hashTags, other.hashTags)) {
            return false;
        }
        if (!Objects.equals(this.blogs, other.blogs)) {
            return false;
        }
        return true;
    }

}
