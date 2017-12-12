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
    private final String user;
    private final int userId;
    private final String accessToken;
    private LocalDateTime lastAccessed;
    
    public SessionData(String user, int userId) {
        lastAccessed = LocalDateTime.now();
        this.user = user;
        this.userId = userId;
        String rand = RandomStringUtils.random(16);
        String token = user+rand;
        accessToken = Base64.getEncoder().encodeToString(token.getBytes());
    }
    
    public String getUser() {
        return user;
    }
    
    public int getUserId() {
        return userId;
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
