/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.components;

/**
 *
 * @author derek
 */
public class SubredditListing {
    private String modhash;
    private String whitelist_status;
    private SubredditListingChild[] children;
    private String after;
    private String before;
    
    public String getModHash() {
        return modhash;
    }
    
    public String getWhitelistStatus() {
        return whitelist_status;
    }
    
    public SubredditListingChild[] getChildren() {
        return children;
    }
    
    public String getAfter() {
        return after;
    }
    
    public String getBefore() {
        return before;
    }
}
