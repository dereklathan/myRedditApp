/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.google.gson.JsonSyntaxException;
import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.rest.client.response.RedditResponse;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author derek
 * @param <T>
 */

@Named
@Dependent
public class GetClient<T extends RedditResponse> extends RedditClient {
    
    public T doGet(RedditUserClientInfo redditUserClientInfo, String path, Class<T> responseClass, Map<String, String> queryParams) {
        initClient(redditUserClientInfo);
        WebTarget target = client.target(url).path(path);
        if(queryParams != null) {
            for(String key : queryParams.keySet()) {
                target = target.queryParam(key, queryParams.get(key));
            }
        }
        Response res = null;
        for(int attempts=0;attempts<retries;) {
            try {
                res = target.request(MediaType.APPLICATION_JSON_TYPE)
                        .header("Authorization", authHeader)
                        .header("User-Agent", userAgent)
                        .get();
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
            try {
                response = this.gsonUtil.getGson().fromJson(json, responseClass);
            }
            catch(JsonSyntaxException ex) {
                try {
                    response = responseClass.newInstance();
                    response.setError("There was an error handling that request");
                    ex.printStackTrace();
                }
                catch(InstantiationException | IllegalAccessException e) {                
                    throw new RuntimeException(e.getCause().getMessage());
                }
                
                
            }
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
    
    
    public T doGet(RedditUserClientInfo redditUserClientInfo, String path, Class<T> responseClass) {
        return this.doGet(redditUserClientInfo, path, responseClass, null);
    }
    
    /**
     * 
     * @param redditUserClientInfo
     * @param path
     * @param queryParams
     * @return raw JSON for building POJOs
     */
    public String doGet(RedditUserClientInfo redditUserClientInfo, String path, Map<String,String> queryParams) {
        initClient(redditUserClientInfo);
        WebTarget target = client.target(url).path(path);
        if(queryParams != null) {
            for(String key : queryParams.keySet()) {
                target = target.queryParam(key, queryParams.get(key));
            }
        }
        Response res = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", authHeader)
                .header("User-Agent", userAgent)
                .get();
        String response;
        if(res.getStatus() == 200) {
            updateToken(res);
            response = res.readEntity(String.class);
        }
        else {
            response = "received invalid response " + Integer.toString(res.getStatus());
        }
        res.close();
        client.close();
        return response;
    }
    
    public String doGet(RedditUserClientInfo redditUserClientInfo, String path) {
        return doGet(redditUserClientInfo, path, (Map<String,String>)null);
    }
    
    
}


