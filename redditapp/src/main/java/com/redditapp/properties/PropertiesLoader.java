/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author derek
 */
public class PropertiesLoader {
    protected Properties prop = new Properties();
    private InputStream input;
    
    public PropertiesLoader(String filename) {
        input = null;
	try {

		input = new FileInputStream(filename);

		// load a properties file
		prop.load(input);

		// get the property value and print it out
                prop.load(input);

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}        
    }
    
}
