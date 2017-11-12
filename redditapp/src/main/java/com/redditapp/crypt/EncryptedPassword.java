/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.crypt;

/**
 *
 * @author derek
 */
public class EncryptedPassword {
    private final String salt;
    private final String password;
    
    public EncryptedPassword(String password, String salt) {
        this.salt = salt;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getSalt() {
        return salt;
    }
   
}
