/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.SubredditListing;

/**
 *
 * @author derek
 */
public class SubredditListingResponse extends RedditResponse {
    private String kind;
    private SubredditListing data;
    
    public String getKind() {
        return kind;
    }
    
    public SubredditListing getListing() {
        return data;
    }
}
