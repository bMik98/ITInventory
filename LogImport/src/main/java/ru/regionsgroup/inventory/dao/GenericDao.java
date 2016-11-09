package ru.regionsgroup.inventory.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, K extends Serializable> {
    K save(T object);

    void update(T object);

    void saveOrUpdate(T object);

    void saveOrUpdate(List<T> object);

    void delete(T object);

    T getById(K key);

    List<T> getAll();
}
