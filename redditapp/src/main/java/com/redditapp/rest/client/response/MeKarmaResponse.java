/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.KarmaList;

/**
 *
 * @author derek
 */
public class MeKarmaResponse extends RedditResponse {
    private String kind;
    private KarmaList[] data;
    
    public MeKarmaResponse() {
        
    }
    
    public MeKarmaResponse(String error) {
        super(error);
    }
    
    public String getKind() {
        return kind;
    }
    
    public KarmaList[] getKarmaList() {
        return data;
    }
}
