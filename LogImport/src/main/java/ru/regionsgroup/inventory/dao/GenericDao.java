package ru.regionsgroup.inventory.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {
    K save(T entity);

    void update(T entity);

    void saveOrUpdate(T entity);

    void saveOrUpdate(List<T> entities);

    void delete(T entity);

    void delete(List<T> entities);

    T getById(K key);

    List<T> getAll();
}
