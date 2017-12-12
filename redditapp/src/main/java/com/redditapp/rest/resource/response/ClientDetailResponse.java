/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response;

import com.redditapp.rest.resource.response.components.ClientInfo;

/**
 *
 * @author derek
 */
public class ClientDetailResponse {
    private ClientInfo client;
    
    public void setClient(ClientInfo clientInfo) {
        this.client = clientInfo;
    }
}
