package ru.regionsgroup.inventory.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.regionsgroup.inventory.dao.GenericDao;

import java.io.Serializable;
import java.util.List;

@Transactional
public class HibernateGenericDao<T, K extends Serializable> implements GenericDao<T, K> {
    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entityType;

    public HibernateGenericDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public K save(T entity) {
        return (K) getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdate(List<T> entities) {
        for (T entity : entities) {
            System.out.println(entity);
            getSession().saveOrUpdate(entity);
        }
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public T getById(K key) {
        return getSession().get(entityType, key);
    }

    @Override
    public List<T> getAll() {
        Query query = getSession().createQuery(String.format("from %s", entityType.getName()));
        return query.list();
    }

    public Class<T> getEntityType() {
        return entityType;
    }
}
