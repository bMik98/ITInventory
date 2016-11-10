package ru.regionsgroup.inventory.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.regionsgroup.inventory.dao.GenericDao;
import ru.regionsgroup.inventory.service.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

@Transactional
public class HibernateGenericDao<T, K extends Serializable> implements GenericDao<T, K> {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Class<T> entityType;

    public HibernateGenericDao(Class<T> entityType) {
        this.entityType = entityType;
    }

    protected Session getSession() {
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
        getSession().beginTransaction();
        getSession().saveOrUpdate(entity);
        getSession().getTransaction().commit();
    }

    @Override
    public void saveOrUpdate(List<T> entities) {
        entities.forEach(this::saveOrUpdate);
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
