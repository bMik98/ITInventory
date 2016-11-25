package ru.regionsgroup.inventory.dao.impl;

import ru.regionsgroup.inventory.dao.GenericComputerDependedDao;
import ru.regionsgroup.inventory.model.Computer;

import java.io.Serializable;

/**
 * @author Mbedritskiy
 */
public abstract class HibernateGenericComputerDependedDao<T, K extends Serializable>
        extends HibernateGenericDao<T, K>
        implements GenericComputerDependedDao<T, K> {

    public HibernateGenericComputerDependedDao(Class entityType) {
        super(entityType);
    }

    @Override
    public int deleteByComputer(final Computer computer) {
        final String query = String.format(
                "delete %s p where p.computerId = :computerId", getEntityType().getSimpleName());
        return getSession()
                .createQuery(query)
                .setParameter("computerId", computer.getComputerId())
                .executeUpdate();
    }
}
