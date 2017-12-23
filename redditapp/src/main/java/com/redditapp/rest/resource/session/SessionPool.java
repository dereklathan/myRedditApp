/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.session;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


/**
 *
 * @author derek
 */
@Named
@Singleton
@ApplicationScoped
public class SessionPool {
    Map<String,SessionData> sessions;
    
    public SessionPool() {
        sessions = new HashMap();
    }
    
    public SessionData getSession(String key) {
        SessionData session =  sessions.get(key);
        if(session == null) {
            return null;
        }
        else {
            session.setLastAccessed(LocalDateTime.now());
            return session;
        }
    }
    
    public String addSession(SessionData session) {
        sessions.put(session.getAccessToken(), session);
        return session.getAccessToken();
    }
    
    public Map<String,SessionData> getAllSessions() {
        return sessions;
    }
    
    public void removeSession(String key) {
        sessions.remove(sessions.get(key).getAccessToken());
    }
}
