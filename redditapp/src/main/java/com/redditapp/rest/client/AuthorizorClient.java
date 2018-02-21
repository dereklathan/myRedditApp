/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.crypt.AES256Util;
import com.redditapp.crypt.EncryptedPassword;
import com.redditapp.rest.client.response.AccessTokenResponse;
import com.redditapp.entity.RedditUserClientInfo;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Entity;
import java.util.Base64;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.redditapp.properties.Properties;
import java.time.LocalDateTime;
import javax.inject.Inject;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

/**
 *
 * @author derek
 */

@Named
public class AuthorizorClient {
    
    @Inject private AES256Util aesUtil;
    @Inject private Properties properties;
    
    public AccessTokenResponse retrieveToken(String code, String redirectURI, RedditUserClientInfo redditUserClientInfo) {
        Form form = new Form();
        form.param("grant_type", "authorization_code");
        form.param("code", code);
        form.param("redirect_uri", redirectURI);
        return postTokenForm(redditUserClientInfo, form);
    }

    public AccessTokenResponse refreshToken(RedditUserClientInfo redditUserClientInfo) {
        if (redditUserClientInfo.getTokenInfo() != null) {
            String refresh = redditUserClientInfo.getTokenInfo().getRefresh();
            String salt = redditUserClientInfo.getTokenInfo().getSalt();
            if (refresh != null) {
                EncryptedPassword pass = new EncryptedPassword(refresh, salt);
                String refreshToken = this.aesUtil.decrypt(pass);
                LocalDateTime expiration = redditUserClientInfo.getTokenInfo().getExpiration();
                if (LocalDateTime.now().isAfter(expiration)) {
                    Form form = new Form();
                    form.param("grant_type", "refresh_token");
                    form.param("refresh_token", refreshToken);
                    return postTokenForm(redditUserClientInfo, form);
                } else {
                    return new AccessTokenResponse("Your token is not expired");
                }
            } else {
                return new AccessTokenResponse("You do not have a refresh token.");
            }
        } else {
            return new AccessTokenResponse("You never obtained an access token.");
        }
    }
    
    private AccessTokenResponse postTokenForm(RedditUserClientInfo redditUserClientInfo, Form form) {
        StringBuilder usernameAndPassword = new StringBuilder();
        String clientId = redditUserClientInfo.getClientInfo().getClientId();
        String clientSecret = redditUserClientInfo.getClientInfo().getClientSecret();
        String redditUserName = redditUserClientInfo.getRedditUser().getUsername();
        String userAgent = "/u/" + redditUserName + "'s client for reddit";
        usernameAndPassword.append(redditUserClientInfo.getClientInfo().getClientId()).append(":");
        if(clientSecret != null && !clientSecret.isEmpty()) {            
            EncryptedPassword encryptedPassword = new EncryptedPassword(clientSecret, redditUserClientInfo.getClientInfo().getSalt());
            usernameAndPassword.append(aesUtil.decrypt(encryptedPassword));
        }
        String authorizationHeaderValue = "Basic " + Base64.getEncoder().encodeToString(usernameAndPassword.toString().getBytes());
        ClientConfig configuration = new ClientConfig()
                .property(ClientProperties.CONNECT_TIMEOUT, properties.getRedditClientProperties().getConnectTimeout())
                .property(ClientProperties.READ_TIMEOUT, properties.getRedditClientProperties().getReadTimeout());
        JerseyClient client = JerseyClientBuilder.createClient(configuration);
        WebTarget target = client.target(properties.getRedditClientProperties().getAuthUrl()).path("api/v1/access_token");
        int retries = properties.getRedditClientProperties().getRetries();
        Response res = null;
        for(int attempts = 0;attempts<retries;) {
            try {
                res = target.request(MediaType.APPLICATION_JSON_TYPE)
                        .header("Authorization", authorizationHeaderValue)
                        .header("User-Agent", userAgent)
                        .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
                break;
            }
            catch(RuntimeException ex) {
                attempts++;
                if(attempts == retries) {
                    throw ex;
                }
            }
        }
        Gson gson = new Gson();
        AccessTokenResponse accessTokenResponse;
        if(res.getStatus() == 200) {
            String json = res.readEntity(String.class);
            accessTokenResponse = gson.fromJson(json, AccessTokenResponse.class);
        }
        else{
            
            accessTokenResponse = new AccessTokenResponse("Received invalid response (" + Integer.toString(res.getStatus()) + ").");
        }
        res.close();
        return accessTokenResponse;        
    } 
}
