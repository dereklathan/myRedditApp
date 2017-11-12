/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.components;

/**
 *
 * @author derek
 */
public class KarmaList {
    private String sr;
    private int comment_karma;
    private int link_karma;
    
    public String getSubreddit() {
        return sr;
    }
    
    public int getCommentKarma() {
        return comment_karma;
    }
    
    public int getLinkKarma() {
        return link_karma;
    }
}
