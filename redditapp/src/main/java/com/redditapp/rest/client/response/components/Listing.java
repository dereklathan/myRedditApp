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
public class Listing extends Thing{
    protected String modhash;
    protected String whitelist_status;
    protected String after;
    protected String before;
    
    public String getModHash() {
        return modhash;
    }
    
    public String getWhitelistStatus() {
        return whitelist_status;
    }
    
    public String getAfter() {
        return after;
    }
    
    public String getBefore() {
        return before;
    }
}
