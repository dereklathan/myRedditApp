/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.ClientInfo;
import com.redditapp.entity.RedditUser;
import com.redditapp.entity.RedditUserClientInfo;
import java.util.Collections;
import java.util.List;
import javax.inject.Named;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author derek
 */
@Named
public class RedditUserClientInfoDao extends BaseDao<RedditUserClientInfo> {
    public List<RedditUserClientInfo> getRedditUserClientInfosByRedditUser(RedditUser redditUser) {
        List<RedditUserClientInfo> results;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<RedditUserClientInfo> query = session
                    .createQuery("from RedditUserClientInfo r where r.redditUser = :redditUser", RedditUserClientInfo.class)
                    .setParameter("redditUser", redditUser);
            results = query.list();
            session.getTransaction().commit();
        }
        return results;
    }
    
    public List<RedditUserClientInfo> getRedditUserClientInfosByClientInfo(ClientInfo clientInfo) {
        List<RedditUserClientInfo> results;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<RedditUserClientInfo> query = session
                    .createQuery("from RedditUserClientInfo r where r.clientInfo = :clientInfo", RedditUserClientInfo.class)
                    .setParameter("clientInfo", clientInfo);
            results = query.list();
            session.getTransaction().commit();
        }
        return results;
    }
    
    public RedditUserClientInfo getRedditUserClientInfo(RedditUser redditUser, ClientInfo clientInfo) {
        RedditUserClientInfo redditUserClientInfo = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            try {
                Query query = session
                        .createQuery("from RedditUserClientInfo r where r.redditUser = :redditUser and r.clientInfo = :clientInfo")
                        .setParameter("redditUser", redditUser)
                        .setParameter("clientInfo", clientInfo);
                redditUserClientInfo = (RedditUserClientInfo)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //redditUserClientInfo = null
            }
            session.getTransaction().commit();
        }
        return redditUserClientInfo;
    }
    
    @Override
    public void saveOrUpdate(RedditUserClientInfo redditUserClientInfo) {
        RedditUserClientInfo checkRedditUserClientInfo = this.getRedditUserClientInfo(redditUserClientInfo.getRedditUser(), redditUserClientInfo.getClientInfo());
        if(checkRedditUserClientInfo != null) {
            redditUserClientInfo.setCreated(checkRedditUserClientInfo.getCreated());
            redditUserClientInfo.setId(checkRedditUserClientInfo.getId());
        }
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(redditUserClientInfo);
            session.getTransaction().commit();
        }
    }
}