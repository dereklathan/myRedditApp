/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response;

import com.redditapp.rest.resource.response.components.ClientInfo;
import java.util.List;

/**
 *
 * @author derek
 */
public class ClientsResponse {
    List<ClientInfo> clients;
    
    public void setClients(List<ClientInfo> clients) {
        this.clients = clients;
    }
}
