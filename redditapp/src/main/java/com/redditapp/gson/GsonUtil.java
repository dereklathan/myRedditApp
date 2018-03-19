/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.redditapp.rest.client.response.CommentListingResponse;
import com.redditapp.rest.client.response.CommentResponse;
import com.redditapp.rest.client.response.MoreCommentsResponse;
import com.redditapp.rest.client.response.deserializer.CommentListingResponseDeserializer;
import com.redditapp.rest.client.response.deserializer.CommentResponseDeserializer;
import com.redditapp.rest.client.response.deserializer.MoreCommentsResponseDeserializer;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author derek
 */
@Singleton
@ApplicationScoped
public class GsonUtil {

    private final Gson gson;

    public GsonUtil() {
        gson = new GsonBuilder()
                .registerTypeAdapter(CommentListingResponse.class, new CommentListingResponseDeserializer())
                .registerTypeAdapter(MoreCommentsResponse.class, new MoreCommentsResponseDeserializer())
                .registerTypeAdapter(CommentResponse.class, new CommentResponseDeserializer())
                .create();
    }
    
    public Gson getGson() {
        return gson;
    }
}
