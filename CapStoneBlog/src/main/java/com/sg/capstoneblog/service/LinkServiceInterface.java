/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.service;

import com.sg.capstoneblog.model.Link;
import java.util.List;

/**
 *
 * @author chris
 */
public interface LinkServiceInterface {

    public List<Link> getMainLinks();
    
    public Link getLinkBySiteDataId(int siteDataId);
    
    public Link updateLink(Link link);
}
