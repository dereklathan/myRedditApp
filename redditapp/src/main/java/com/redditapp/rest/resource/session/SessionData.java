/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.session;

import java.util.Base64;
import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author derek
 */
public class SessionData {
    private String user;
    private final String accessToken;
    private LocalDateTime lastAccessed;
    
    public SessionData(String user) {
        lastAccessed = LocalDateTime.now();
        String rand = RandomStringUtils.random(16);
        String token = user+rand;
        accessToken = Base64.getEncoder().encodeToString(token.getBytes());
    }
    
    public String getUser() {
        return user;
    }
    
    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }
    
    public void setLastAccessed(LocalDateTime time) {
        this.lastAccessed = time;
    }
    
    public String getAccessToken() {
        return accessToken;
    }

}
