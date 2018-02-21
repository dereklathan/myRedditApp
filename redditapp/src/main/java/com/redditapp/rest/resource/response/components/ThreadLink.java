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
public class ThreadLink {
    private String title;
    private String thingId;
    private String url;
    private String selfText;
    private String author;
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setThingId(String thingId) {
        this.thingId = thingId;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setSelfText(String selfText) {
        this.selfText = selfText;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
}
