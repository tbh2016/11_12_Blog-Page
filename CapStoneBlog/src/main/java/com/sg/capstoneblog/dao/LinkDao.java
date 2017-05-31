/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.capstoneblog.dao;

import com.sg.capstoneblog.model.Link;
import java.util.List;

/**
 *
 * @author chris
 */
public interface LinkDao {

    public Link addLink(Link link);

    public Link getLink(int linkId);

    public void deleteLink(int linkId);

    public Link updateLink(Link link);

    public List<Link> getAllLinks();

    public List<Link> getMainSiteLinks();

    public Link getLinkForSite(int siteId);
}
