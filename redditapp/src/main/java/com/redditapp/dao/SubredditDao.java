/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.Subreddit;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author derek
 */
@Named
@ApplicationScoped
public class SubredditDao extends BaseDao<Subreddit> {
    
    public Subreddit getByName(String name) {
        Subreddit subreddit = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("Subreddit.findByName");
            query.setParameter("name", name);
            try {
                subreddit = (Subreddit)query.getSingleResult();
            }
            catch(NoResultException ex) {
                // subreddit = null
            }
        }
        return subreddit;
    }
    
}
