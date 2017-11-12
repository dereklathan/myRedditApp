/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource;
import com.redditapp.authorization.AuthorizationUtil;
import com.redditapp.rest.client.AuthorizorClient;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.redditapp.dao.RedditUserDao;
import com.redditapp.entity.RedditUser;
import com.redditapp.dao.ClientInfoDao;
import com.redditapp.dao.RedditUserClientInfoDao;
import com.redditapp.entity.ClientInfo;
import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.entity.TokenInfo;
import javax.ws.rs.PathParam;
import com.redditapp.rest.client.response.AccessTokenResponse;
import com.redditapp.dao.TokenInfoDao;
import com.redditapp.rest.client.AccountClient;
import com.redditapp.rest.client.ListingsClient;
import com.redditapp.rest.client.response.SubredditListingResponse;
import com.redditapp.rest.client.response.components.KarmaList;
import com.redditapp.rest.client.response.components.SubredditListingChild;
import java.time.LocalDateTime;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author derek
 */
@RequestScoped
@Path("/authorizor")
public class Authorizor {
    
    @Inject private RedditUserDao redditUserDao;
    @Inject private ClientInfoDao clientInfoDao;
    @Inject private RedditUserClientInfoDao redditUserClientInfoDao;
    @Inject private TokenInfoDao tokenInfoDao;
    @Inject private AuthorizorClient authorizorClient;
    @Inject private AuthorizationUtil authorizationUtil;
    @Inject private AccountClient accountClient;
    @Inject private ListingsClient listingsClient;
    
    @Context
    UriInfo uri;
    
    /*
        This is to be used as the redirect uri when registering your client
    */
    @GET
    @Path("/{redditUserName}/{clientId}/authorize")
    @Produces({MediaType.TEXT_HTML})
    public String authorize(@PathParam("redditUserName") String redditUserName, @PathParam("clientId") String clientId, @QueryParam("state") String state, @QueryParam("code") String code) {
        RedditUser redditUser = redditUserDao.getRedditUserByUserName(redditUserName);
        ClientInfo clientInfo = clientInfoDao.getClientInfoByClientId(clientId);
        RedditUserClientInfo redditUserClientInfo = redditUserClientInfoDao.getRedditUserClientInfo(redditUser, clientInfo);
        String redirectURI = uri.getAbsolutePath().toString();
        String authorizeResponse;
        if(state != null && code != null && state.compareTo(clientInfo.getState()) == 0) {
           if(authorizationUtil.authorize(code, redirectURI, redditUserClientInfo)) {
               authorizeResponse = "obtained access token";
               // reddit is giving incorrect token expiration 
               // but gives correct one in response header on api request
               // 600 requests and expires in ~6 minutes instead of
               // 60 requests and expires in 60 minutes
               // so we have to burn a request
               accountClient.getMe(redditUserClientInfo); 
           }
           else {
               authorizeResponse = "an internal error occurred";
           }
        }
        else {
            authorizeResponse = "state didn't match";
        }
        return parseResponse(authorizeResponse);
    }
    
    
    private static String parseResponse(String authorizeResponse) {
                String html = "<!DOCTYPE html>\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Start Page</title>\n" +
"        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
"    </head>\n" +
"    <body>\n" +
"        <br>" +
         authorizeResponse + "\n" +
"    </body>\n" +
"</html>";
        
        return html;
    }
}