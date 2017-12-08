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
public class RedditUser {
    private int id;
    private String username;
    
    public void setId(int id) {
        this.id = id;      
    }
    
    public int getId() {
        return id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
}
