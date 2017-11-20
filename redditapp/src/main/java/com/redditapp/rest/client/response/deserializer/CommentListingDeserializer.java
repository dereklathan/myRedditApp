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
import com.google.gson.JsonParseException;
import com.redditapp.rest.client.response.components.Comment;
import com.redditapp.rest.client.response.components.CommentListing;
import com.redditapp.rest.client.response.components.More;
import com.redditapp.rest.client.response.components.Thing;
import java.lang.reflect.Type;

/**
 *
 * @author derek
 */
public class CommentListingDeserializer implements JsonDeserializer<CommentListing>{
    
    @Override
    public CommentListing deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(CommentListing.class, new CommentListingDeserializer());
        Gson gson = gsonBuilder.create();
        Gson gsonComment = new Gson();
        JsonArray jsonArray = json.getAsJsonArray();
        CommentListing commentListing = new CommentListing();
        for(JsonElement element : jsonArray) {
            Thing thing = gson.fromJson(element, Thing.class);
            JsonElement dataElement = element.getAsJsonObject().get("data");
            if(thing.getKind().contentEquals("t1")) {
                Comment comment = gsonComment.fromJson(dataElement, Comment.class);
                if(!dataElement.getAsJsonObject().get("replies").isJsonNull()) {
                    JsonElement children = dataElement.getAsJsonObject()
                            .getAsJsonObject("replies")
                            .getAsJsonObject("data")
                            .getAsJsonArray("children");
                    comment.setretReplies(gson.fromJson(children, CommentListing.class));
                }
                commentListing.addComment(comment);
            }
            else if(thing.getKind().contentEquals("more")) {
                More more = gson.fromJson(dataElement, More.class);
                commentListing.setMore(more);
            }
        }
        return commentListing;              
    }
}