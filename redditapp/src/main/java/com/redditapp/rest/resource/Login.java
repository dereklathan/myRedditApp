/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource;

import com.google.gson.Gson;
import com.redditapp.dao.UserDao;
import com.redditapp.entity.User;
import com.redditapp.rest.resource.filter.AuthFilter;
import com.redditapp.rest.resource.request.LoginRequest;
import com.redditapp.rest.resource.response.LoginResponse;
import com.redditapp.rest.resource.response.ValidateResponse;
import com.redditapp.rest.resource.session.SessionData;
import com.redditapp.rest.resource.session.SessionPool;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@Path("auth")
public class Login extends Resource {
    @Inject SessionPool sessionPool;
    @Inject UserDao userDao;
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Path("login")
    public Response login(String request) {
        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(request, LoginRequest.class);
        User user = userDao.getUserByUsername(loginRequest.getUsername());
        if(user != null) {
            String hashPass = user.getPass();
            String salt = user.getSalt();
            String pass = loginRequest.getPassword();
            if(getSHA512SecurePassword(pass,salt).contentEquals(hashPass)) {
                SessionData session = new SessionData(loginRequest.getUsername());
                String token = sessionPool.addSession(session);
                LoginResponse response = new LoginResponse();
                response.setAccessToken(token);
                String responseJson = gson.toJson(response, LoginResponse.class);
                return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
            }
        }  
        return Response.serverError().status(403).header("Access-Control-Allow-Origin", "*").build();
    }
    


    @GET
    @AuthFilter
    @Produces({MediaType.TEXT_PLAIN})
    @Path("validate")
    public Response validate(@Context HttpHeaders headers) {
        Gson gson = new Gson();
        ValidateResponse response = new ValidateResponse();
        String responseJson = gson.toJson(response, ValidateResponse.class);
        return Response.ok(responseJson).header("Access-Control-Allow-Origin", "*").build();
    }
    
    private static String getSHA512SecurePassword(String passwordToHash, String   salt){
        String generatedPassword = null;
        try {
             MessageDigest md = MessageDigest.getInstance("SHA-512");
             md.update(salt.getBytes());
             byte[] bytes = md.digest(passwordToHash.getBytes());
             StringBuilder sb = new StringBuilder();
             for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }    
}