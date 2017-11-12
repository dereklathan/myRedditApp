/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.rest.client.response.SubredditListingResponse;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author derek
 */
public class ListingsClient {
    @Inject GetClient<SubredditListingResponse> subredditListingClient;
    
    public SubredditListingResponse getPopularSubreddits(RedditUserClientInfo redditUserClientInfo) {
        return subredditListingClient.doGet(redditUserClientInfo, "/subreddits/popular", SubredditListingResponse.class);
    }
    
    public SubredditListingResponse getNextPopularSubreddits(RedditUserClientInfo redditUserClientInfo, String afterKey) {
        Map<String, String> queryParams = new HashMap();
        queryParams.put("after", afterKey);
        return subredditListingClient.doGet(redditUserClientInfo, "/subreddits/popular", SubredditListingResponse.class, queryParams);
    }
    
    
}
