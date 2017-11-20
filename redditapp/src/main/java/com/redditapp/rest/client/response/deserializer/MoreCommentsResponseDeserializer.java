/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.deserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.redditapp.rest.client.response.MoreCommentsResponse;
import com.redditapp.rest.client.response.components.CommentListing;
import java.lang.reflect.Type;

/**
 *
 * @author derek
 */
public class MoreCommentsResponseDeserializer implements JsonDeserializer<MoreCommentsResponse>{
    
    @Override
    public MoreCommentsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(CommentListing.class, new CommentListingDeserializer())
                .create();
        MoreCommentsResponse response = new MoreCommentsResponse();
        //Reddit gives an empty string if no replies
        String rawJson = json.toString().replace("\"replies\":\"\"", "\"replies\": null");
        json = new JsonParser().parse(rawJson).getAsJsonObject().getAsJsonObject("json");
        JsonElement errorsJson = json.getAsJsonObject().getAsJsonArray("errors");
        JsonElement commentListingJson = json.getAsJsonObject().getAsJsonObject("data").getAsJsonArray("things");
        String[] errors = gson.fromJson(errorsJson, String[].class);
        CommentListing moreComments = gson.fromJson(commentListingJson, CommentListing.class);
        response.setErrors(errors);
        response.setMoreComments(moreComments);
        return response;
    }
    
}
