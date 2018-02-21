/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.request;

/**
 *
 * @author derek
 */
public class VoteRequest {
    private String thingId;
    private String permalink;
    private int score;
    
    public String getThingId() {
        return thingId;
    }
    
    public String getPermalink() {
        return permalink;
    }
    
    public int getScore() {
        return score;
    }
}
