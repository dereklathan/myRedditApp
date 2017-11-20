/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.redditapp.rest.client.response.CommentListingResponse;
import com.redditapp.rest.client.response.components.CommentListing;
import com.redditapp.rest.client.response.components.ThreadLinkListingWrapper;
import java.lang.reflect.Type;

/**
 *
 * @author derek
 */
public class CommentListingResponseDeserializer implements JsonDeserializer<CommentListingResponse>{
    
    @Override
    public CommentListingResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(CommentListing.class, new CommentListingDeserializer())
                .create();
        //Reddit gives an empty string if no replies
        String rawJson = json.toString().replace("\"replies\":\"\"", "\"replies\": null");
        json = new JsonParser().parse(rawJson);
        JsonArray response = json.getAsJsonArray();
        JsonElement threadLinkListingWrapperJson = response.get(0);
        ThreadLinkListingWrapper threadLinkListingWrapper = gson.fromJson(threadLinkListingWrapperJson, ThreadLinkListingWrapper.class);
        JsonElement commentListingWrapperJson = response.get(1);
        JsonObject commentListingDataJson = commentListingWrapperJson.getAsJsonObject().getAsJsonObject("data");
        JsonElement commentListingChildren = commentListingDataJson.getAsJsonArray("children");        
        CommentListing commentListing = gson.fromJson(commentListingChildren, CommentListing.class);
        return new CommentListingResponse(threadLinkListingWrapper.getThreadLinkListing().getChildren()[0].getThreadLink(), commentListing);
    }
    
}
