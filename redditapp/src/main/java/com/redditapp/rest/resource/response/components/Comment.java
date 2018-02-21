/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response.components;

/**
 *
 * @author derek
 */
public class Comment {
    private String body;
    private String author;
    private String thingId;
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setThingId(String thingId) {
        this.thingId = thingId;
    }
}
