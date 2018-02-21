/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client;

import com.redditapp.entity.RedditUserClientInfo;
import com.redditapp.rest.client.response.CommentResponse;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Form;

/**
 *
 * @author derek
 */

@Named
public class CommentClient {
    
    @Inject private PostClient<CommentResponse> commentClient;
    
    public CommentResponse postComment(RedditUserClientInfo redditUserClientInfo, String parent_id, String comment) {
        Form form = new Form();
        form.param("api_type", "json");
        form.param("text", comment);
        form.param("thing_id", parent_id);
        return commentClient.doPost(redditUserClientInfo, "/api/comment", form, CommentResponse.class);
    }
}
