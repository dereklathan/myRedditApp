/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response;

import com.redditapp.rest.client.response.components.Features;

/**
 *
 * @author derek
 */
public class MeResponse extends RedditResponse{
    private boolean is_employee;
    private Features features;
    private boolean pref_no_profanity;
    private boolean is_suspended;
    private String pref_geopopular;
    private Object subreddit;
    private boolean is_sponsor;
    private Object gold_expiration;
    private String id;
    private Object suspension_expiration_utc;
    private boolean verified;
    private Object new_modmail_exists;
    private boolean over_18;
    private boolean is_gold;
    private boolean is_mod;
    private boolean has_verified_email;
    private boolean has_mod_mail;
    private String oauth_client_id;
    private boolean hide_from_robots;
    private int link_karma;
    private int inbox_count;
    private Object pref_top_karma_subreddits;
    private boolean has_mail;
    private boolean pref_show_snoovatar;
    private String name;
    private float created;
    private int gold_creddits;
    private float created_utc;
    private boolean in_beta;
    private int comment_karma;
    private boolean has_subscribed;
    
    public MeResponse() {
        
    }
    
    public MeResponse(String error) {
        super(error);
    }
    
    public boolean isEmployee() {
        return is_employee;
    }
    
    public Features getFeatures() {
        return features;
    }
    
    public boolean prefNoProfanity() {
        return pref_no_profanity;
    }
    
    public boolean isSuspended() {
        return is_suspended;
    }
    
    public String prefGeopopular() {
        return pref_geopopular;
    }
    
    public boolean isSponsor() {
        return is_sponsor;
    }
    
    public String getId() {
        return id;
    }
    
    public boolean isVerified() {
        return verified;
    }
    
    public boolean isOver18() {
        return over_18;
    }
    
    public boolean isGold() {
        return is_gold;
    }
    
    public boolean isMod() {
        return is_mod;
    }
    
    public boolean hasVerifiedEmail() {
        return has_verified_email;
    }
    
    public boolean hasModMail() {
        return has_mod_mail;
    }
    
    public String getOauthClientId() {
        return oauth_client_id;
    }
    
    public boolean hiddenFromRobots() {
        return hide_from_robots;
    }
    
    public int getLinkKarma() {
        return link_karma;
    }
    
    public int getInboxCount() {
        return inbox_count;
    }
    
    public boolean hasMail() {
        return has_mail;
    }
    
    public boolean prefShowSnoovatar() {
        return pref_show_snoovatar;
    }
    
    public String getName() {
        return name;
    }
    
    public float getCreated() {
        return created;
    }
    
    public int getGoldCreddits() {
        return gold_creddits;
    }
    
    public float getCreatedUTC() {
        return created_utc;
    }
    
    public boolean isInBeta() {
        return in_beta;
    }
    
    public int getCommentKarma() {
        return comment_karma;
    }
    
    public boolean hasSubscribed() {
        return has_subscribed;
    }
}
