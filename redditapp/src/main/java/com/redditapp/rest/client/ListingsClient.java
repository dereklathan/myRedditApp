/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.rest.client.response.CommentListingResponse;
import com.redditapp.rest.client.response.LinkListingResponse;
import com.redditapp.rest.client.response.SubredditListingResponse;
import com.redditapp.rest.client.response.MoreCommentsResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/**
 *
 * @author derek
 */
public class ListingsClient {
    @Inject GetClient<SubredditListingResponse> subredditListingClient;
    @Inject GetClient<LinkListingResponse> linkListingClient;
    @Inject GetClient<CommentListingResponse> commentListingClient;
    @Inject GetClient<MoreCommentsResponse> moreCommentsListingClient;
    
    public SubredditListingResponse getPopularSubreddits(RedditUserClientInfo redditUserClientInfo) {
        return subredditListingClient.doGet(redditUserClientInfo, "/subreddits/popular", SubredditListingResponse.class);
    }
    
    public SubredditListingResponse getNextPopularSubreddits(RedditUserClientInfo redditUserClientInfo, String afterKey) {
        Map<String, String> queryParams = new HashMap();
        queryParams.put("after", afterKey);
        return subredditListingClient.doGet(redditUserClientInfo, "/subreddits/popular", SubredditListingResponse.class, queryParams);
    }
    
    public LinkListingResponse getLinks(RedditUserClientInfo redditUserClientInfo, String path) {
        return linkListingClient.doGet(redditUserClientInfo, path, LinkListingResponse.class);
    }
    
    public LinkListingResponse getNextLinks(RedditUserClientInfo redditUserClientInfo, String path, String afterKey) {
        Map<String, String> queryParams = new HashMap();
        queryParams.put("after", afterKey);
        return linkListingClient.doGet(redditUserClientInfo, path, LinkListingResponse.class, queryParams);
    }
    
    public CommentListingResponse getComments(RedditUserClientInfo redditUserClientInfo, String path) {
        return commentListingClient.doGet(redditUserClientInfo, path, CommentListingResponse.class);
    }
    
    public MoreCommentsResponse getMoreComments(RedditUserClientInfo redditUserClientInfo, String linkId, List<String> childIds) {
         Map<String,String> queryParams = new HashMap();
        StringBuilder childListBuilder = new StringBuilder();
        for(String c : childIds) {
            childListBuilder
                    .append(c)
                    .append(",");
        }
        childListBuilder.deleteCharAt(childListBuilder.length()-1);
        queryParams.put("api_type", "json");
        queryParams.put("link_id", linkId);
        queryParams.put("children", childListBuilder.toString());
        return moreCommentsListingClient.doGet(redditUserClientInfo, "/api/morechildren", MoreCommentsResponse.class, queryParams);       
    }
    
    //this method is for testing
    public String checkEndpoint(RedditUserClientInfo redditUserClientInfo, String path) {
        return subredditListingClient.doGet(redditUserClientInfo, path);
    }
    
    
}
