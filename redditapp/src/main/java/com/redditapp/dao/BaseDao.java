/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redditapp.dao;
import com.redditapp.entity.BaseEntity;
import com.redditapp.hibernate.HibernateUtil;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
/**
 *
 * @author derek
 * @param <T> extends BaseEntity
 */
@Named
@Dependent 
public class BaseDao<T extends BaseEntity> {
    @Inject private HibernateUtil hibernateUtil;
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
    
    public T getById(int id, Class<T> cls) {
        T entity = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query query = session
                    .createQuery("from " + cls.getSimpleName() + " t where t.id = :id", cls)
                    .setParameter("id", id);
            try {
                entity = (T)query.getSingleResult();
            }
            catch(NoResultException ex) {
                //entity = null;
            }
        }
        return entity;
    }
}
