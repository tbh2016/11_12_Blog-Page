/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.commanddto.StaticSite;

/**
 *
 * @author jayce
 */
public interface StaticSitesServiceLayerInterface {
    
    public StaticSite getStaticSite(int siteId);
}
