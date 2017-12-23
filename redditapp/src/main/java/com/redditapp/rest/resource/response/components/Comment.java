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
    private String name;
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
