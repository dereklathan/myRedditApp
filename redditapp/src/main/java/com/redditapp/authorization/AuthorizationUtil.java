/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.authorization;

import com.redditapp.dao.RedditUserClientInfoDao;
import com.redditapp.dao.TokenInfoDao;
import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.entity.TokenInfo;
import com.redditapp.rest.client.AuthorizorClient;
import com.redditapp.rest.client.response.AccessTokenResponse;
import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author derek
 */
@Named
public class AuthorizationUtil {
   @Inject AuthorizorClient authorizorClient;
   @Inject RedditUserClientInfoDao redditUserClientInfoDao;
   @Inject TokenInfoDao tokenInfoDao;
   
   public boolean authorize(String code, String redirectUri, RedditUserClientInfo redditUserClientInfo) {   
        AccessTokenResponse accessTokenResponse = authorizorClient.retrieveToken(code, redirectUri, redditUserClientInfo);
        if(accessTokenResponse.getError() == null || accessTokenResponse.getError().isEmpty()) {
            TokenInfo tokenInfo = new TokenInfo();
            LocalDateTime expirationDate = LocalDateTime.now().plusSeconds(new Long(accessTokenResponse.getExpiration()));
            tokenInfo.setExpiration(expirationDate);
            tokenInfo.setAccessToken(accessTokenResponse.getAccessToken());
            tokenInfo.setRefreshToken(accessTokenResponse.getRefreshToken());
            tokenInfo.setTokenType(accessTokenResponse.getTokenType());
            tokenInfo.setScope(accessTokenResponse.getScope());
            tokenInfo.setRemainingRequests(60);
            redditUserClientInfo.setTokenInfo(tokenInfo);
            redditUserClientInfoDao.saveOrUpdate(redditUserClientInfo);
            return true;
        }
        else {
            System.out.println("error: " + accessTokenResponse.getError());
            return false;
        }
   }
   
   public boolean refreshToken(RedditUserClientInfo redditUserClientInfo) {
        AccessTokenResponse accessTokenResponse = authorizorClient.refreshToken(redditUserClientInfo);
        if(accessTokenResponse.getError() == null || accessTokenResponse.getError().isEmpty()) {
            TokenInfo tokenInfo = redditUserClientInfo.getTokenInfo();
            tokenInfo.setAccessToken(accessTokenResponse.getAccessToken());
            LocalDateTime expiration = LocalDateTime.now().plusSeconds(new Long(accessTokenResponse.getExpiration()));
            tokenInfo.setExpiration(expiration);
            tokenInfo.setRemainingRequests(60);
            tokenInfoDao.saveOrUpdate(tokenInfo);
            return true;
        }
        return false;
   }
}
