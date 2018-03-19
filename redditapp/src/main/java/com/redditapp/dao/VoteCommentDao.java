/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.VoteComment;
import java.util.List;
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
public class VoteCommentDao extends BaseDao<VoteComment> {

    public VoteComment getByRedditUserIdAndCommentId(int redditUserId, int commentId) {
        VoteComment voteComment = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteComment v where v.redditUser.id = :redditUserId and v.comment.id = :commentId", VoteComment.class)
                    .setParameter("redditUserId", redditUserId)
                    .setParameter("commentId", commentId);
            try {
                voteComment = (VoteComment)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //voteComment = null;
            }
            session.getTransaction().commit();
        }
        return voteComment;
    }
    
    public VoteComment getByRedditUserIdAndThingId(int redditUserId, String thingId) {
        VoteComment voteComment = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteComment v where v.redditUser.id = :redditUserId and v.comment.thingId = :thingId", VoteComment.class)
                    .setParameter("redditUserId", redditUserId)
                    .setParameter("thingId", thingId);
            try {
                voteComment = (VoteComment)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //voteComment = null;
            }
            session.getTransaction().commit();
        }
        return voteComment;
    }
    
    public List<VoteComment> getByAddedByIdAndThingId(int userId, String thingId) {
        List<VoteComment> results;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteComment v where v.redditUser.addedBy.id = :userId and v.comment.thingId = :thingId", VoteComment.class)
                    .setParameter("userId", userId)
                    .setParameter("thingId", thingId);
            results = query.list();
            session.getTransaction().commit();
        }
        return results;
    }
    
    public List<VoteComment> getByThingId(String thingId) {
        List<VoteComment> results;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteComment v where v.comment.thingId = :thingId", VoteComment.class)
                    .setParameter("thingId", thingId);
            results = query.list();
            session.getTransaction().commit();
        }
        return results;
    }
}
