/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

/**
 *
 * @author derek
 */
public class AccessTokenResponse extends RedditResponse {
    
    private String access_token;
    private String token_type;
    private int expires_in;
    private String refresh_token;
    private String scope;
    
    public AccessTokenResponse() {
        
    }
    
    public AccessTokenResponse(String error) {
        super(error);
    }
    
    public String getAccessToken() {
        return access_token;
    }
    
    public String getTokenType() {
        return token_type;
    }
    
    public int getExpiration() {
        return expires_in;
    }
    
    public String getRefreshToken() {
        return refresh_token;
    }
    
    public String getScope() {
        return scope;
    }
    
}
