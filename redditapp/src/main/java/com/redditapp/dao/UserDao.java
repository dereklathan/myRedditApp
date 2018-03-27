/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.User;
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
public class UserDao extends BaseDao<User> {
    
    public User getUserById(int userId) {
        User user = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("User.findById");
            query.setParameter("id", userId);
            try {
                user = (User)query.getSingleResult();
            }
            catch(NoResultException ex) {
                // user = null
            }
        }
        return user;
    }
    
    public User getUserByUsername(String username) {
        User user = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("User.findByUsername");
            query.setParameter("username", username);
            try {
                user = (User)query.getSingleResult();
            }
            catch(NoResultException ex) {
                // user = null
            }
        }
        return user;
    }
    
    @Override
    public void saveOrUpdate(User user) {
        User checkUser = getUserByUsername(user.getUsername());
        if(checkUser != null) {
            user.setCreated(checkUser.getCreated());
            user.setId(checkUser.getId());
        }
        super.saveOrUpdate(user);
    }
    
}
