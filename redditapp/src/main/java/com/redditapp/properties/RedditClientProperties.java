/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.properties;

/**
 *
 * @author derek
 */
public class RedditClientProperties extends PropertiesLoader{
    public RedditClientProperties() {
        super("redditclient.properties");
    }
    
    public String getAuthUrl() {
        return prop.getProperty("auth_url");
    }
    
    public String getApiUrl() {
        return prop.getProperty("api_url");
    }
    
    public int getConnectTimeout() {
        return Integer.parseInt(prop.getProperty("connect_timeout"));
    }
    
    public int getReadTimeout() {
        return Integer.parseInt(prop.getProperty("read_timeout"));
    }
    
    public int getRetries() {
        return Integer.parseInt(prop.getProperty("retries"));
    }
    
}
