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
public class ClientInfo {
    private int id;
    private String authUrl;
    private String redirectUrl;
    private String clientId;
    private String clientName;
    private boolean canDelete;
    private int redditUserId;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }
    
    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
    
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    
    public void setRedditUserId(int redditUserId) {
        this.redditUserId = redditUserId;
    }
}
