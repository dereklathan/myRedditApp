/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.properties;

/**
 *
 * @author derek
 */
public class KeyfileProperties extends PropertiesLoader {
    public KeyfileProperties() {
        super("keyfile.properties");
    }
    
    public String getKeyfilePath() {
        return prop.getProperty("path");
    }
}
