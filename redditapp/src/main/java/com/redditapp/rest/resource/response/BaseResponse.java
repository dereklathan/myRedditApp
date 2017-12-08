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
public class BaseResponse {
    protected String error;
    
    public void setError(String error) {
        this.error = error;
    }
}
