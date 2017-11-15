/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.components;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author derek
 */
public class CommentListing extends Listing {
    private final List<Comment> comments;
    private More more;
    
    public CommentListing() {
        this.kind = "CommentListing";
        this.comments = new ArrayList();
        more = null;
    }
    
    public List<Comment> getComments() {
        return comments;
    }
    
    public void addComment(Comment comment) {
        comments.add(comment);
    }
    
    public More getMore() {
        return more;
    }
    
    public void setMore(More more) {
        this.more = more;
    }
}
