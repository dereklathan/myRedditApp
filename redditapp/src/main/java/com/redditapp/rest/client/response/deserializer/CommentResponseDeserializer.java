/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.redditapp.rest.client.response.CommentResponse;
import com.redditapp.rest.client.response.components.Comment;
import java.lang.reflect.Type;

/**
 *
 * @author derek
 */
public class CommentResponseDeserializer implements JsonDeserializer<CommentResponse> {
    @Override
    public CommentResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        CommentResponse response = new CommentResponse();
        //Reddit gives an empty string if no replies
        String rawJson = json.toString().replace("\"replies\":\"\"", "\"replies\": null");
        json = new JsonParser().parse(rawJson).getAsJsonObject().getAsJsonObject("json");
        JsonElement errorsJson = json.getAsJsonObject().getAsJsonArray("errors");
        String[] errors = gson.fromJson(errorsJson, String[].class);
        response.setErrors(errors);
        JsonElement thingsJson = json.getAsJsonObject().getAsJsonObject("data").getAsJsonArray("things");
        if(thingsJson != null) {
            JsonArray thingsJsonArray = thingsJson.getAsJsonArray();
            if(thingsJsonArray.size() == 1) {
                JsonElement commentJson = thingsJsonArray.get(0).getAsJsonObject().getAsJsonObject("data");
                Comment comment = gson.fromJson(commentJson, Comment.class);
                response.setComment(comment);
            }
        }
        return response;
    }
}
