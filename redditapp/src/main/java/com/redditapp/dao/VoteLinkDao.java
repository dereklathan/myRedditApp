/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.VoteLink;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author derek
 */
public class VoteLinkDao extends BaseDao<VoteLink> {
    
    public VoteLink getByRedditUserIdAndLinkId(int redditUserId, int linkId) {
        VoteLink voteLink = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteLink v where v.redditUser.id = :redditUserId and v.link.id = :linkId", VoteLink.class)
                    .setParameter("redditUserId", redditUserId)
                    .setParameter("linkId", linkId);
            try {
                voteLink = (VoteLink)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //voteLink = null;
            }
            session.getTransaction().commit();
        }
        return voteLink;
    }
    
    public VoteLink getByRedditUserIdAndThingId(int redditUserId, String thingId) {
        VoteLink voteLink = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteLink v where v.redditUser.id = :redditUserId and v.link.thingId = :thingId", VoteLink.class)
                    .setParameter("redditUserId", redditUserId)
                    .setParameter("thingId", thingId);
            try {
                voteLink = (VoteLink)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //voteLink = null;
            }
            session.getTransaction().commit();
        }
        return voteLink;
    }
    
    public List<VoteLink> getByAddedByIdAndThingId(int userId, String thingId) {
        List<VoteLink> results;
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteLink v where v.redditUser.addedBy.id = :userId and v.link.thingId = :thingId", VoteLink.class)
                    .setParameter("userId", userId)
                    .setParameter("thingId", thingId);
            results = query.list();
            session.getTransaction().commit();
        }
        return results;
    }
    
    public List<VoteLink> getByThingId(String thingId) {
        List<VoteLink> results;
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from VoteLink v where v.link.thingId = :thingId", VoteLink.class)
                    .setParameter("thingId", thingId);
            results = query.list();
            session.getTransaction().commit();
        }
        return results;
    }
}
