/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response;

/**
 *
 * @author derek
 */
public class LoginResponse {
    String accessToken;
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
