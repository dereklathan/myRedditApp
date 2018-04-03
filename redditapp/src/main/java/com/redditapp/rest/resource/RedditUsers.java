/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource;

import com.redditapp.crypt.AES256Util;
import com.redditapp.crypt.EncryptedPassword;
import com.redditapp.dao.ClientInfoDao;
import com.redditapp.dao.RedditUserClientInfoDao;
import com.redditapp.dao.RedditUserDao;
import com.redditapp.dao.UserDao;
import com.redditapp.entity.RedditUser;
import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.entity.User;
import com.redditapp.gson.GsonUtil;
import com.redditapp.rest.resource.filter.AuthFilter;
import com.redditapp.rest.resource.request.AddRedditUserRequest;
import com.redditapp.rest.resource.response.BaseResponse;
import com.redditapp.rest.resource.response.RedditUserResponse;
import com.redditapp.rest.resource.session.SessionPool;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    
    @Inject private RedditUserDao redditUserDao;
    @Inject private UserDao userDao;
    @Inject private SessionPool sessionPool;
    @Inject private AES256Util aesUtil;
    @Inject private RedditUserClientInfoDao redditUserClientInfoDao;
    @Inject private ClientInfoDao clientInfoDao;
    @Inject private GsonUtil gsonUtil;
    
    @GET
    @Path("getredditusers")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRedditUsers(@Context HttpHeaders headers) {
        int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
        RedditUserResponse response = new RedditUserResponse();
        List<RedditUser> redditUsers = redditUserDao.getRedditUsersAddedById(userId);
        boolean canDelete;
        for(RedditUser redditUser : redditUsers) {
            canDelete = this.redditUserClientInfoDao.getAuthorizedByRedditUserIdAddedBy(redditUser.getId(), userId).isEmpty();
            response.addRedditUser(redditUser.getId(), redditUser.getUsername(), canDelete);
        }
        String responseJson = this.gsonUtil.getGson().toJson(response, RedditUserResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @POST
    @Path("addreddituser")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response addRedditUser(@Context HttpHeaders headers, String json) {
        AddRedditUserRequest request = this.gsonUtil.getGson().fromJson(json, AddRedditUserRequest.class);
        RedditUserResponse response = new RedditUserResponse();
        RedditUser redditUser = redditUserDao.getRedditUserByUserName(request.getUsername());
        String responseJson;
        if(redditUser == null) {
            int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
            List<RedditUser> redditUsers = this.redditUserDao.getRedditUsersAddedById(userId);
            int unauthcount = 0;
            for (RedditUser r : redditUsers) {
                if (this.redditUserClientInfoDao.getAuthorizedByRedditUserIdAddedBy(r.getId(), userId).isEmpty()) {
                    unauthcount++;
                }
                if (unauthcount == 2) {
                    //2 is the limit on reddit accounts with no authorized clients
                    response.setError("Unauthorized Reddit user limit reached.");
                    responseJson = this.gsonUtil.getGson().toJson(response, RedditUserResponse.class);
                    return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
                }
            }
            User user = this.userDao.getUserById(userId);
            redditUser = new RedditUser();
            redditUser.setUsername(request.getUsername());
            EncryptedPassword pass = this.aesUtil.encrypt(request.getPassword());
            redditUser.setPassword(pass.getPassword());
            redditUser.setSalt(pass.getSalt());
            redditUser.setAddedBy(user);
            this.redditUserDao.saveOrUpdate(redditUser);
            response.addRedditUser(redditUser.getId(), request.getUsername(), true);
        } else {
            response.setError("User already exists.");
        }
        responseJson = this.gsonUtil.getGson().toJson(response, RedditUserResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @POST
    @Path("deletereddituser")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteRedditUser(@Context HttpHeaders headers, @QueryParam("id") int redditUserId) {
        int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
        RedditUser redditUser = this.redditUserDao.getRedditUserByIdAndAddedById(redditUserId, userId);
        if(redditUser == null) {
            return Response.serverError().status(403).header("Access-Control-Allow-Origin", "*").build();
        }
        String responseJson;
        BaseResponse response = new BaseResponse();
        List<RedditUserClientInfo> redditUserClientInfos = this.redditUserClientInfoDao.getRedditUserClientInfosByRedditUser(redditUser);
        for(RedditUserClientInfo r : redditUserClientInfos) {
            if(r.getTokenInfo() != null) {
                response.setError("Reddit user has authorized client(s)");
                responseJson = this.gsonUtil.getGson().toJson(response, BaseResponse.class);
                return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
            }
        }
        for(RedditUserClientInfo r : redditUserClientInfos) {
            this.redditUserClientInfoDao.delete(r);
            this.clientInfoDao.delete(r.getClientInfo());
        }
        this.redditUserDao.delete(redditUser);
        responseJson = this.gsonUtil.getGson().toJson(response, BaseResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
}
