/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import com.redditapp.rest.resource.Authorizor;
import com.redditapp.rest.resource.Clients;
import com.redditapp.rest.resource.Login;
import com.redditapp.rest.resource.RedditUsers;
import com.redditapp.rest.resource.TestComment;
import com.redditapp.rest.resource.TestListings;
import com.redditapp.rest.resource.TestUser;
import com.redditapp.rest.resource.TestVote;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author derek
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(Authorizor.class);
        resources.add(Login.class);
        resources.add(RedditUsers.class);
        resources.add(Clients.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
    }
    
}
