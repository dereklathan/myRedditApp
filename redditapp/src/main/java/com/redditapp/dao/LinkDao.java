/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.Link;
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
public class LinkDao extends BaseDao<Link> {
    
    public Link getByPermalink(String permalink) {
        Link link = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("Link.findByPermalink");
            query.setParameter("permalink", permalink);
            try {
                link = (Link)query.getSingleResult();
            }
            catch(NoResultException ex) {
                // link = null
            }
        }
        return link;
    }
    
    public Link getByThingId(String thingId) {
        Link link = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("Link.findByThingId");
            query.setParameter("thingId", thingId);
            try {
                link = (Link)query.getSingleResult();
            }
            catch(NoResultException ex) {
                // link = null;
            }
        }
        return link;
    }
    
}
