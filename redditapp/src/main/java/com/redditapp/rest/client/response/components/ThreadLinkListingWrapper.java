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
public class ThreadLinkListingWrapper {
    private String kind;
    private ThreadLinkListing data;
    
    public String getKind() {
        return kind;
    }
    
    public ThreadLinkListing getThreadLinkListing() {
        return data;
    }
    
}
