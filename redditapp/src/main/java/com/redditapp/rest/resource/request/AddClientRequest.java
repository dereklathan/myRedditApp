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
public class AddClientRequest {
    private String clientName;
    private String clientId;
    private String clientSecret;
    private int redditUserId;
    
    public String getClientName() {
        return clientName;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    public int getRedditUserId() {
        return redditUserId;
    }
}
