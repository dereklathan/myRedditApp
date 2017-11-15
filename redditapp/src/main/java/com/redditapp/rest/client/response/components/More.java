/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.rest.client.response.components;

/**
 *
 * @author derek
 */
public class More {
    private Integer count;
    private String name;
    private String id;
    private String parent_id;
    private Integer depth;
    private String[] children;
    
    public Integer getCount() {
        return count;
    }
    
    public String getName() {
        return name;
    }
    
    public String getId() {
        return id;
    }
    
    public String getParentId() {
        return parent_id;
    }
    
    public Integer getDepth() {
        return depth;
    }
    
    public String[] getChildren() {
        return children;
    }
}
