/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.request;

/**
 *
 * @author derek
 */
public class LoginRequest {
    String username;
    String password;
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
}
