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
public class HashTag {
    private int hashTagId;
    private String hashTag;

    public int getHashTagId() {
        return hashTagId;
    }

    public void setHashTagId(int hashTagId) {
        this.hashTagId = hashTagId;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.hashTagId;
        hash = 89 * hash + Objects.hashCode(this.hashTag);
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
        final HashTag other = (HashTag) obj;
        if (this.hashTagId != other.hashTagId) {
            return false;
        }
        if (!Objects.equals(this.hashTag, other.hashTag)) {
            return false;
        }
        return true;
    }

    
}
