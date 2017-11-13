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
public class LinkListing extends Listing {
    private LinkListingChild[] children;
    
    public LinkListingChild[] getChildren() {
        return children;
    }
}
