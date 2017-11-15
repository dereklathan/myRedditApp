/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.CommentListing;
import com.redditapp.rest.client.response.components.ThreadLinkListing;


/**
 *
 * @author derek
 */
public class CommentListingResponse extends RedditResponse {
    private final ThreadLinkListing threadLinkListing;
    private final CommentListing commentListing;
    
    public CommentListingResponse(ThreadLinkListing threadLinkListing, CommentListing commentListing) {
        this.threadLinkListing = threadLinkListing;
        this.commentListing = commentListing;
    }
    
    public ThreadLinkListing getThreadLinkListing() {
        return threadLinkListing;
    }
    
    public CommentListing getCommentListing() {
        return commentListing;
    }
}
