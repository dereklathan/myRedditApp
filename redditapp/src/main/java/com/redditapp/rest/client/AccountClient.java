/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.entity.RedditUserClientInfo;
import javax.inject.Named;
import com.redditapp.rest.client.response.MeKarmaResponse;
import com.redditapp.rest.client.response.MeResponse;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


/**
 *
 * @author derek
 */
@Named
@ApplicationScoped
public class AccountClient {
    @Inject private GetClient<MeResponse> meClient;
    @Inject private GetClient<MeKarmaResponse> meKarmaClient;

    
    public MeResponse getMe(RedditUserClientInfo redditUserClientInfo) {
        return meClient.doGet(redditUserClientInfo, "api/v1/me", MeResponse.class);
    }
    
    public MeKarmaResponse getMeKarma(RedditUserClientInfo redditUserClientInfo) {
        return meKarmaClient.doGet(redditUserClientInfo, "api/v1/me/karma", MeKarmaResponse.class);
    }
    
}
