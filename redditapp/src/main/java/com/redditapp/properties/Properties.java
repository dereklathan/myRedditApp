/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.properties;

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author derek
 */

@Singleton
@ApplicationScoped
public class Properties {
    private final KeyfileProperties keyfileProperties;
    private final RedditClientProperties redditClientProperties;
    
    public Properties() {
        keyfileProperties = new KeyfileProperties();
        redditClientProperties = new RedditClientProperties();
    }
    
    public KeyfileProperties getKeyfileProperties() {
        return keyfileProperties;
    }
    
    public RedditClientProperties getRedditClientProperties() {
        return redditClientProperties;
    }
    
}
