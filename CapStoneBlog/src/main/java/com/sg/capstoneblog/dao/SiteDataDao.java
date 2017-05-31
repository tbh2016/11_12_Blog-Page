/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.SiteData;
import java.util.List;

/**
 *
 * @author yingy
 */
public interface SiteDataDao {

    public SiteData getSite(int siteId);

    public SiteData addSite(SiteData site);

    public void updateSite(SiteData site);

    public void deleteSite(int siteId);

    public SiteData getHomePage();

    public SiteData getBlogPage();

    public List<SiteData> getRecentBlogs(int numberOfBlogs);

    public List<SiteData> getSitesByHashTag(int hashTagId);

    public List<SiteData> getSitesByCategory(int categoryId);

    public List<SiteData> getAllSites();

    public List<SiteData> getAllBlogs();

    public List<SiteData> getAllStaticSites();

}
