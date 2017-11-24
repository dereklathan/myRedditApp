/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.rest.client.response.RedditResponse;
import javax.inject.Named;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author derek
 * @param <T>
 * 
 */
@Named
public class PostClient<T extends RedditResponse> extends RedditClient {
    
public T doPost(RedditUserClientInfo redditUserClientInfo, String path, Form form, Class<T> responseClass) {
        initClient(redditUserClientInfo);
        WebTarget target = client.target(url).path(path);
        Response res = null;
        for(int attempts=0;attempts<retries;) {
            try {
                res = target.request(MediaType.APPLICATION_JSON_TYPE)
                        .header("Authorization", authHeader)
                        .header("User-Agent", userAgent)
                        .post(Entity.form(form));
                break;
            }
            catch(RuntimeException ex) {
                attempts++;
                if(attempts == retries) {
                    throw ex;
                }
            }
        }
        T response;
        if(res.getStatus() == 200) {
            updateToken(res);
            String json = res.readEntity(String.class);
            response = gson.fromJson(json, responseClass);
        }
        else {
            
            try {
                response = responseClass.newInstance();
                response.setError("received invalid response " + Integer.toString(res.getStatus()));
            }
            catch(InstantiationException | IllegalAccessException ex) {                
                throw new RuntimeException(ex.getCause().getMessage());
            }
            
        }
        res.close();
        client.close();
        return response;
    }
    
    
    public String doPost(RedditUserClientInfo redditUserClientInfo, String path, Form form) {
        initClient(redditUserClientInfo);
        WebTarget target = client.target(url).path(path);

        
        Response res = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .header("User-Agent", userAgent)
                .post(Entity.form(form));
        String response;
        if(res.getStatus() == 200) {
            updateToken(res);
            response = res.readEntity(String.class);
        }
        else {
            System.out.println(res.readEntity(String.class));
            response = "received invalid response " + Integer.toString(res.getStatus());
        }
        res.close();
        client.close();
        return response;
    }
    

    
}
