/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;
import com.redditapp.entity.BaseEntity;
import com.redditapp.hibernate.HibernateUtil;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author derek
 * @param <T extends BaseEntity>
 */
@Named
public class BaseDao<T extends BaseEntity> {
    @Inject HibernateUtil hibernateUtil;
    protected SessionFactory sessionFactory;
   
    public BaseDao() {
       
    }
    
    @PostConstruct
    private void init() {
        sessionFactory = hibernateUtil.getSessionFactory();
    }
    
    public void saveOrUpdate(T entity) {
        try(Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        }
    }
    
    public void delete(T entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
