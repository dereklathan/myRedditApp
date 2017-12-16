/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response;

import com.redditapp.rest.resource.response.components.ClientInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author derek
 */
public class ClientsResponse extends BaseResponse {
    private List<ClientInfo> clients;
    private String redditUserName;
    
    public ClientsResponse() {
        this.clients = new ArrayList();
    }
    
    public void addClient(ClientInfo client) {
        this.clients.add(client);
    }
    
    public void setClients(List<ClientInfo> clients) {
        this.clients = clients;
    }
    
    public void setRedditUserName(String redditUserName) {
        this.redditUserName = redditUserName;
    }
}
