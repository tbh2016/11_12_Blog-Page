/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.model;

import java.util.Objects;


/**
 *
 * @author yingy
 */
public class PageType {
    private int pageTypeId;
    private String pageType;

    public int getPageTypeId() {
        return pageTypeId;
    }

    public void setPageTypeId(int pageTypeId) {
        this.pageTypeId = pageTypeId;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.pageTypeId;
        hash = 79 * hash + Objects.hashCode(this.pageType);
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
        final PageType other = (PageType) obj;
        if (this.pageTypeId != other.pageTypeId) {
            return false;
        }
        if (!Objects.equals(this.pageType, other.pageType)) {
            return false;
        }
        return true;
    }
    
    
    
}
