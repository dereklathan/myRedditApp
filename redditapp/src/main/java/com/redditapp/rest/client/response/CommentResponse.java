/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.Comment;

/**
 *
 * @author derek
 */
public class CommentResponse extends RedditResponse {
    private String[] errors;
    private Comment comment;
    
    public String[] getErrors() {
        return errors;
    }
    
    public void setErrors(String[] errors) {
        this.errors = errors;
    }
    
    public Comment getComment() {
        return comment;
    }
    
    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
