/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.commanddto;

import com.sg.capstoneblog.model.Category;
import com.sg.capstoneblog.model.Comment;
import com.sg.capstoneblog.model.ContactInfo;
import com.sg.capstoneblog.model.HashTag;
import com.sg.capstoneblog.model.Link;
import com.sg.capstoneblog.model.Picture;
import com.sg.capstoneblog.model.SiteData;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author yingy
 */
public class BlogSite {
    
    private List<Link> links;
    private SiteData siteData;
    private List<Picture> pictures;
    private ContactInfo contact;
    private List<HashTag> hashTags;
    private List<Comment> comments;
    private Category category;

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<HashTag> hashTags) {
        this.hashTags = hashTags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

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
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.links);
        hash = 67 * hash + Objects.hashCode(this.siteData);
        hash = 67 * hash + Objects.hashCode(this.pictures);
        hash = 67 * hash + Objects.hashCode(this.contact);
        hash = 67 * hash + Objects.hashCode(this.hashTags);
        hash = 67 * hash + Objects.hashCode(this.comments);
        hash = 67 * hash + Objects.hashCode(this.category);
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
        final BlogSite other = (BlogSite) obj;
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
        if (!Objects.equals(this.hashTags, other.hashTags)) {
            return false;
        }
        if (!Objects.equals(this.comments, other.comments)) {
            return false;
        }
        if (!Objects.equals(this.category, other.category)) {
            return false;
        }
        return true;
    }

    
}
