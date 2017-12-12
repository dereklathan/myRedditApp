/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource;

import com.google.gson.Gson;
import com.redditapp.crypt.AES256Util;
import com.redditapp.crypt.EncryptedPassword;
import com.redditapp.dao.ClientInfoDao;
import com.redditapp.dao.RedditUserClientInfoDao;
import com.redditapp.dao.RedditUserDao;
import com.redditapp.dao.UserDao;
import com.redditapp.entity.RedditUser;
import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.rest.resource.filter.AuthFilter;
import com.redditapp.rest.resource.request.AddClientRequest;
import com.redditapp.rest.resource.request.ClientsRequest;
import com.redditapp.rest.resource.response.BaseResponse;
import com.redditapp.rest.resource.response.ClientDetailResponse;
import com.redditapp.rest.resource.response.ClientsResponse;
import com.redditapp.rest.resource.response.components.ClientInfo;
import com.redditapp.rest.resource.session.SessionPool;
import java.util.ArrayList;
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

@Path("clients")
public class Clients extends Resource {
    @Inject ClientInfoDao clientInfoDao;
    @Inject RedditUserClientInfoDao redditUserClientInfoDao;
    @Inject RedditUserDao redditUserDao;
    @Inject UserDao userDao;
    @Inject SessionPool sessionPool;
    @Inject AES256Util aesUtil;
    
    @GET
    @Path("getclients")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClients(@Context HttpHeaders headers, @QueryParam("id") int redditUserId) {
        Gson gson = new Gson();
        int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
        RedditUser redditUser = this.redditUserDao.getRedditUserByIdAndAddedById(redditUserId, userId);
        if(redditUser == null) {
            return Response.serverError().status(403).header("Access-Control-Allow-Origin", "*").build();
        }
        List<RedditUserClientInfo> redditUserClientInfos = redditUserClientInfoDao
                .getRedditUserClientInfosByRedditUser(redditUser);
        ClientsResponse response = new ClientsResponse();
        List<ClientInfo> clients = new ArrayList();
        for(RedditUserClientInfo r : redditUserClientInfos) {
            ClientInfo client = new ClientInfo();
            client.setCanDelete(r.getTokenInfo() == null);
            client.setClientId(r.getClientInfo().getClientId());
            client.setClientName(r.getClientInfo().getName());
            client.setId(r.getClientInfo().getId());
            clients.add(client);
        }
        response.setClients(clients);
        String responseJson = gson.toJson(response, ClientsResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @GET
    @Path("getclient")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response getClientDetail(@Context HttpHeaders headers, @QueryParam("id") int clientId) {
        Gson gson = new Gson();
        int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
        RedditUserClientInfo redditUserClientInfo = this.redditUserClientInfoDao.getRedditUserClientInfoAddedByIdAndClientInfoId(userId, clientId);
        if(redditUserClientInfo == null) {
            return Response.serverError().status(403).header("Access-Control-Allow-Origin", "*").build();
        }
        com.redditapp.entity.ClientInfo clientInfo = redditUserClientInfo.getClientInfo();
        ClientInfo clientInfoResponse = new ClientInfo();
        ClientDetailResponse response = new ClientDetailResponse();
        clientInfoResponse.setId(clientInfo.getId());
        clientInfoResponse.setClientName(clientInfo.getName());
        clientInfoResponse.setClientId(clientInfo.getClientId());
        String redirectUrl;
        String authUrl;
        if (redditUserClientInfo.getTokenInfo() == null) {
            redirectUrl = "http://localhost:8080/redditapp-1.0-SNAPSHOT/rest/authorizor/"
                    + redditUserClientInfo.getRedditUser().getUsername() + "/"
                    + clientInfo.getClientId() + "/authorize";
            authUrl = "https://www.reddit.com/api/v1/authorize?client_id="
                    + clientInfo.getClientId() + "&response_type=code&state="
                    + clientInfo.getState() + "&redirect_uri=" + redirectUrl
                    + "&duration=permanent&scope=identity,edit,flair,history,modconfig,"
                    + "modflair,modlog,modposts,modwiki,mysubreddits,privatemessages,read,"
                    + "report,save,submit,subscribe,vote,wikiedit,wikiread";
        } else {
            redirectUrl = "";
            authUrl = "";
        }
        clientInfoResponse.setAuthUrl(authUrl);
        clientInfoResponse.setRedirectUrl(redirectUrl);
        response.setClient(clientInfoResponse);
        String responseJson = gson.toJson(response, ClientDetailResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @POST
    @Path("addclient")
    @AuthFilter
    @Produces({MediaType.APPLICATION_JSON})
    public Response addClient(@Context HttpHeaders headers, String request) {
        Gson gson = new Gson();
        AddClientRequest addClientRequest = gson.fromJson(request, AddClientRequest.class);
        String responseJson;
        com.redditapp.entity.ClientInfo clientInfo = this.clientInfoDao.getClientInfoByClientId(addClientRequest.getClientId());
        BaseResponse response = new BaseResponse();
        if(clientInfo == null) {
            int userId = this.sessionPool.getSession(headers.getHeaderString("access-token")).getUserId();
            RedditUser redditUser = this.redditUserDao.getRedditUserByIdAndAddedById(addClientRequest.getRedditUserId(), userId);
            if(redditUser == null) {
                return Response.serverError().status(403).header("Access-Control-Allow-Origin", "*").build();
            }
            if(this.redditUserClientInfoDao.getUnauthorizedByRedditUserIdAddedBy(redditUser.getId(), userId).size() > 1) {
                response.setError("You have reached your limit on unauthorized clients for this Reddit user.");
                responseJson = gson.toJson(response, BaseResponse.class);
                return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
            }
            clientInfo = new com.redditapp.entity.ClientInfo();
            clientInfo.setClientId(addClientRequest.getClientId());
            EncryptedPassword pass = this.aesUtil.encrypt(addClientRequest.getClientSecret());
            clientInfo.setClientSecret(pass.getPassword());
            clientInfo.setSalt(pass.getSalt());
            clientInfo.setName(addClientRequest.getClientName());
            this.clientInfoDao.saveOrUpdate(clientInfo);
            RedditUserClientInfo redditUserClientInfo = new RedditUserClientInfo();
            redditUserClientInfo.setClientInfo(clientInfo);
            redditUserClientInfo.setRedditUser(redditUser);
            this.redditUserClientInfoDao.saveOrUpdate(redditUserClientInfo);
        }
        else {
            response.setError("Client with ID already exists.");
        }
        responseJson = gson.toJson(response, BaseResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }

}
