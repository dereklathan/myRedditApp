/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response;

import com.redditapp.rest.resource.response.components.Comment;
import com.redditapp.rest.resource.response.components.ThreadLink;

/**
 *
 * @author derek
 */
public class ListingResponse extends BaseResponse {
    private boolean isComment;
    private Comment comment;
    private ThreadLink threadLink;
    private int score;
    private int scoreLimit;
    
    public ListingResponse() {
       this.isComment = false;
       this.threadLink = new ThreadLink();
       this.comment = new Comment();
    }
    
    public void setIsComment(boolean isComment) {
        this.isComment = isComment;
    }
    
    public void setThreadLink(ThreadLink threadLink) {
        this.threadLink = threadLink;
    }
    
    public ThreadLink getThreadLink() {
        return this.threadLink;
    }
    
    public Comment getComment() {
        return this.comment;
    }
    
    public void setScore(int score) {
        this.score = score;
    }
    
    public void setScoreLimit(int scoreLimit) {
        this.scoreLimit = scoreLimit;
    }
}
