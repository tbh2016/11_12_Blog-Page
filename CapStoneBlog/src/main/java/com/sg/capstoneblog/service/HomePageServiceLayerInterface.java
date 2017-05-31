/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.HomeSite;
import com.sg.capstoneblog.model.SiteData;

/**
 *
 * @author chris
 */
public interface HomePageServiceLayerInterface {

    public HomeSite getHomePage();
    
    public void updateSiteData(SiteData siteData);
    
    public SiteData getSiteData(int siteDataId);

//    public void updateHomePage(HomeSite site);
}
