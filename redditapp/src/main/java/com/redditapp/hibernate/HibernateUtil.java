/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.hibernate;


import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 *
 * @author derek
 */

/*
 * Whenever I hot deploy with c3p0 connection pool
 * this creates another connection pool
 * until I eventually run out of DB connections. 
 * Bouncing the server everytime is annoying.
 */

@Singleton
@ApplicationScoped
public class HibernateUtil {
    private SessionFactory sessionFactory;
    
    public HibernateUtil() {
        try {
            StandardServiceRegistry standardRegistry = 
                new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metaData = new MetadataSources(
                standardRegistry).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        }
        catch (Throwable th) {
            System.out.println("Initial SessionFactory creation failed " + th);
            sessionFactory = null;
        }
    }
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
