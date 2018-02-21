/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.rest.client.response.VoteResponse;
import javax.inject.Inject;
import javax.ws.rs.core.Form;

/**
 *
 * @author derek
 */
public class VoteClient {
    @Inject private PostClient<VoteResponse> voteClient;
    
    public VoteResponse voteUp(RedditUserClientInfo redditUserClientInfo, String thingId) {
        Form form = new Form();
        form.param("id", thingId);
        form.param("dir", "1");
        return voteClient.doPost(redditUserClientInfo, "/api/vote", form, VoteResponse.class);
    }
    
    public VoteResponse voteDown(RedditUserClientInfo redditUserClientInfo, String thingId) {
        Form form = new Form();
        form.param("id", thingId);
        form.param("dir", "-1");
        return voteClient.doPost(redditUserClientInfo, "/api/vote", form, VoteResponse.class);
    }
    
    public VoteResponse unvote(RedditUserClientInfo redditUserClientInfo, String thingId) {
        Form form = new Form();
        form.param("id", thingId);
        form.param("dir", "0");
        return voteClient.doPost(redditUserClientInfo, "/api/vote", form, VoteResponse.class);
    }
}
