/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import javax.inject.Named;
import com.redditapp.entity.RedditUser;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author derek
 */
@Named
public class RedditUserDao extends BaseDao<RedditUser> {
    
    public RedditUserDao() {
        super();
    }   
    
    public RedditUser getRedditUserByUserName(String username) {
        RedditUser redditUser = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .getNamedQuery("RedditUser.findByUsername")
                    .setParameter("username", username);
            try {
                redditUser = (RedditUser)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //redditUser = null;
            }
            session.getTransaction().commit();
        }
        return redditUser;
    }
    
    public List<RedditUser> getRedditUsers() {
        List<RedditUser> results = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .getNamedQuery("RedditUser.findAll"); 
            results = (List<RedditUser>)query.getResultList();
            session.getTransaction().commit();
        }
        return results;
    }
}
