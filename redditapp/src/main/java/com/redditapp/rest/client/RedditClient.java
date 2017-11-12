/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.authorization.AuthorizationUtil;
import com.redditapp.dao.TokenInfoDao;
import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.entity.TokenInfo;
import com.redditapp.properties.Properties;
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

/**
 *
 * @author derek
 */
@Named
public class RedditClient {
    protected String url;
    protected TokenInfo tokenInfo;
    protected String authHeader;
    protected String userAgent;
    protected JerseyClient client;
    @Inject private TokenInfoDao tokenInfoDao;
    @Inject private AuthorizationUtil authorizationUtil;
    @Inject private Properties properties;
    
    protected void initClient(RedditUserClientInfo redditUserClientInfo) {
        url = properties.getRedditClientProperties().getApiUrl();
        tokenInfo = redditUserClientInfo.getTokenInfo();
        if(LocalDateTime.now().isAfter(tokenInfo.getExpiration())) {
            authorizationUtil.refreshToken(redditUserClientInfo);
        }
        else if(tokenInfo.getRemainingRequests() == 0) {
            throw new RuntimeException("client is out of requests. try again at " + tokenInfo.getExpiration().toString());
        }
        authHeader = tokenInfo.getTokenType() + " " + tokenInfo.getAccessToken();
        userAgent = "/u/" + redditUserClientInfo.getRedditUser().getUsername() + "'s client for reddit";
        ClientConfig config = new ClientConfig();
        config = config.property(ClientProperties.CONNECT_TIMEOUT, properties.getRedditClientProperties().getConnectTimeout());
        config = config.property(ClientProperties.READ_TIMEOUT, properties.getRedditClientProperties().getReadTimeout());
        client = JerseyClientBuilder.createClient(config);
    }
    
    protected void updateToken(Response res) {
        Long remainingSeconds = Long.parseLong(res.getHeaderString("X-Ratelimit-Reset"));
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(remainingSeconds);
        int remainingRequests = (int)Double.parseDouble(res.getHeaderString("X-Ratelimit-Remaining"));
        tokenInfo.setExpiration(expiration);
        tokenInfo.setRemainingRequests(remainingRequests);
        tokenInfoDao.saveOrUpdate(tokenInfo);
    }
}
