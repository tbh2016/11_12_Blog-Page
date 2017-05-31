/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.model;

import java.util.Objects;

/**
 *
 * @author chris
 */
public class Link {

    private int linkId;
    private String linkName;
    private String linkReference;
    private boolean mainLink;
    private Integer siteId;
    private Integer position;

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkReference() {
        return linkReference;
    }

    public void setLinkReference(String linkReference) {
        this.linkReference = linkReference;
    }

    public boolean isMainLink() {
        return mainLink;
    }

    public void setMainLink(boolean isMainLink) {
        this.mainLink = isMainLink;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.linkId;
        hash = 83 * hash + Objects.hashCode(this.linkName);
        hash = 83 * hash + Objects.hashCode(this.linkReference);
        hash = 83 * hash + (this.mainLink ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.siteId);
        hash = 83 * hash + Objects.hashCode(this.position);
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
        final Link other = (Link) obj;
        if (this.linkId != other.linkId) {
            return false;
        }
        if (this.mainLink != other.mainLink) {
            return false;
        }
        if (!Objects.equals(this.linkName, other.linkName)) {
            return false;
        }
        if (!Objects.equals(this.linkReference, other.linkReference)) {
            return false;
        }
        if (!Objects.equals(this.siteId, other.siteId)) {
            return false;
        }
        if (!Objects.equals(this.position, other.position)) {
            return false;
        }
        return true;
    }

}
