package ru.regionsgroup.inventory.dao.impl;

import ru.regionsgroup.inventory.dao.DomainDao;
import ru.regionsgroup.inventory.model.Domain;

/**
 * @author mbedritskiy
 */
public class HibernateDomainDao extends HibernateGenericDao<Domain, String> implements DomainDao {
    public HibernateDomainDao() {
        super(Domain.class);
    }
}
