/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.CommentListing;
import com.redditapp.rest.client.response.components.ThreadLink;


/**
 *
 * @author derek
 */
public class CommentListingResponse extends RedditResponse {
    private final ThreadLink threadLink;
    private final CommentListing commentListing;
    
    public CommentListingResponse() {
        this.threadLink = null;
        this.commentListing = null;
    }
    
    public CommentListingResponse(ThreadLink threadLink, CommentListing commentListing) {
        this.threadLink = threadLink;
        this.commentListing = commentListing;
    }
    
    public ThreadLink getThreadLink() {
        return threadLink;
    }
    
    public CommentListing getCommentListing() {
        return commentListing;
    }
}
