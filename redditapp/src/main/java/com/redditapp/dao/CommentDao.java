/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.Comment;
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
public class CommentDao extends BaseDao<Comment> {
    public Comment getByPermalink(String permalink) {
        Comment comment = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .getNamedQuery("Comment.findByPermalink")
                    .setParameter("permalink", permalink);
            try {
                comment = (Comment)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //comment = null;
            }
        }
        return comment;
    }
    
    public Comment getByThingId(String thingId) {
        Comment comment = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .getNamedQuery("Comment.findByThingId")
                    .setParameter("thingId", thingId);
            try {
                comment = (Comment)query.getSingleResult();
            }
            catch(NoResultException ex) {
                // comment = null
            }
        }
        return comment;
    }
}
