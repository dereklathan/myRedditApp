/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;

import com.redditapp.entity.TokenInfo;
import java.util.List;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author derek
 */
@Named
public class TokenInfoDao extends BaseDao<TokenInfo> {
    
    public List<TokenInfo> getAll() {
        List<TokenInfo> results;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session.getNamedQuery("TokenInfo.findAll");
            results = query.getResultList();
            session.getTransaction().commit();
        }
        return results;
    }
}
