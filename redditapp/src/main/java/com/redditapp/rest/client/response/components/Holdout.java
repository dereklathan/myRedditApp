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
public class Holdout {
    protected String owner;
    protected String variant;
    protected int experiment_id;
    
    public String getOwner() {
        return owner;
    }
    public String getVariant() {
        return variant;
    }
    public int getExperimentId() {
        return experiment_id;
    }
}
