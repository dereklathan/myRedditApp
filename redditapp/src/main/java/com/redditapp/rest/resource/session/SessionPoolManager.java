/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.session;

import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author derek
 */

@Singleton
@ApplicationScoped
public class SessionPoolManager {
    @Inject SessionPool sessionPool;
    
    public SessionPoolManager() {
    }

     
    @PostConstruct
    @Schedule(hour="*", minute="*", second="0", persistent=false)
    private void clean() {
        Map<String, SessionData> allSessions = sessionPool.getAllSessions();
        System.out.println("total sessions: " + allSessions.size());
        Iterator<SessionData> i = allSessions.values().iterator();
        SessionData session;
        while(i.hasNext()) {
            try {
                session = i.next();
                if(session.getLastAccessed().isBefore(LocalDateTime.now().minusMinutes(10))) {
                    i.remove();
                }
            }
            catch(ConcurrentModificationException ex) {
                ex.printStackTrace();
            }
        }
    } 

}
