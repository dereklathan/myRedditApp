/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.response;

import java.util.List;
import com.redditapp.rest.resource.response.components.RedditUser;
import java.util.ArrayList;

/**
 *
 * @author derek
 */
public class RedditUserResponse extends BaseResponse {
    private List<RedditUser> redditUsers;
    
    public RedditUserResponse() {
        redditUsers = new ArrayList<>();
    }
    
    public void addRedditUser(int id, String username, boolean canDelete) {
        RedditUser redditUser = new RedditUser();
        redditUser.setId(id);
        redditUser.setUsername(username);
        redditUser.setCanDelete(canDelete);
        redditUsers.add(redditUser);
    }
}
