/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource;

import com.google.gson.Gson;
import com.redditapp.dao.RedditUserDao;
import com.redditapp.entity.RedditUser;
import com.redditapp.rest.resource.filter.AuthFilter;
import com.redditapp.rest.resource.request.AddRedditUserRequest;
import com.redditapp.rest.resource.response.RedditUserResponse;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author derek
 */
@Path("redditusers")
public class RedditUsers extends Resource {
    
    @Inject RedditUserDao redditUserDao;
    
    @GET
    @Path("getredditusers")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRedditUsers() {
        Gson gson = new Gson();
        RedditUserResponse response = new RedditUserResponse();
        List<RedditUser> redditUsers = redditUserDao.getRedditUsers();
        for(RedditUser redditUser : redditUsers) {
            response.addRedditUser(redditUser.getId(), redditUser.getUsername());
        }
        String redditUserJson = gson.toJson(response, RedditUserResponse.class);
        System.out.println(redditUserJson);

        return Response.ok(redditUserJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @POST
    @Path("addreddituser")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response addRedditUser(@Context HttpHeaders headers, String json) {
        Gson gson = new Gson();
        AddRedditUserRequest request = gson.fromJson(json, AddRedditUserRequest.class);
        RedditUserResponse response = new RedditUserResponse();
        RedditUser user = redditUserDao.getRedditUserByUserName(request.getUsername());
        if(user == null) {
            //fakeid: not persisting yet
            response.addRedditUser(1, request.getUsername());
        }
        else {
            response.setError("User already exists.");
        }
        String responseJson = gson.toJson(response, RedditUserResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
}
