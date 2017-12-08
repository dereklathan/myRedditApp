/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.resource.filter;
import com.redditapp.rest.resource.session.SessionPool;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author derek
 */
@AuthFilter
@Provider
public class AuthFilterInterceptor implements ContainerRequestFilter {
    @Inject SessionPool sessionPool;
    
    @Context 
    private HttpHeaders headers;
    
    @Override
    public void filter(ContainerRequestContext ctx) throws WebApplicationException {
        String token = headers.getHeaderString("access-token");
        if(token != null && !token.isEmpty()) {
            if(sessionPool.getSession(token) == null) {
                throw new WebApplicationException(Status.UNAUTHORIZED);
            }
        }
        else {
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
        
    }
}
