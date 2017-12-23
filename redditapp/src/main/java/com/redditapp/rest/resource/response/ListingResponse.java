/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response;

import com.redditapp.rest.resource.response.components.Comment;
import com.redditapp.rest.resource.response.components.ThreadLink;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author derek
 */
public class ListingResponse extends BaseResponse {
    private boolean isComment;
    private Comment comment;
    private ThreadLink threadLink;
    private final List<Integer> canUp;
    private final List<Integer> canDown;
    
    public ListingResponse() {
       this.isComment = false;
       this.canUp = new ArrayList();
       this.canDown = new ArrayList();
    }
    
    public void setIsComment(boolean isComment) {
        this.isComment = isComment;
    }
    
    public void setThreadLink(ThreadLink threadLink) {
        this.threadLink = threadLink;
    }
    
    public void setComment(Comment comment) {
        this.comment = comment;
    }
    
    public void addCanUp(int redditUserId) {
        this.canUp.add(redditUserId);
    }
    
    public void addCanDown(int redditUserId) {
        this.canDown.add(redditUserId);
    }
}
