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
public abstract class HibernateGenericDao<T, K extends Serializable> implements GenericDao<T, K> {
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
    public K save(final T entity) {
        return (K) getSession().save(entity);
    }

    @Override
    public void update(final T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(final T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdate(final List<T> entities) {
        for (T entity : entities) {
            getSession().saveOrUpdate(entity);
        }
    }

    @Override
    public void delete(final List<T> entities) {
        for (T entity : entities) {
            getSession().saveOrUpdate(entity);
        }
    }

    @Override
    public void delete(final T entity) {
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
