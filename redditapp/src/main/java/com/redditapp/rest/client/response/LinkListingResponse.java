/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.LinkListing;

/**
 *
 * @author derek
 */
public class LinkListingResponse extends RedditResponse {
    private String kind;
    private LinkListing data;
    
    public String getKind() {
        return kind;
    }
    
    public LinkListing getListing() {
        return data;
    }
}
