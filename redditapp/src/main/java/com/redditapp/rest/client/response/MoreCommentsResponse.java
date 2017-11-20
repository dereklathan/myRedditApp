/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.CommentListing;

/**
 *
 * @author derek
 */
public class MoreCommentsResponse extends RedditResponse {
    private String[] errors;
    private CommentListing moreComments;
    
    public String[] getErrors() {
        return errors;
    }
    
    public CommentListing getMoreComments() {
        return moreComments;
    }
    
    public void setErrors(String[] errors) {
        this.errors = errors;
    }
    
    public void setMoreComments(CommentListing moreComments) {
        this.moreComments = moreComments;
    }
}
