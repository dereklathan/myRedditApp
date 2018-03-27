/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.ClientInfo;
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
public class ClientInfoDao extends BaseDao<ClientInfo> {     
    
    public ClientInfo getClientInfoByClientId(String clientId) {
        ClientInfo clientInfo = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("ClientInfo.findByClientId").setParameter("clientId", clientId);
            try {
                clientInfo = (ClientInfo)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //clientInfo = null
            }
        }
        return clientInfo;
    }
    
}
