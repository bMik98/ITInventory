package ru.regionsgroup.inventory.dao.impl;

import org.springframework.stereotype.Repository;
import ru.regionsgroup.inventory.dao.DomainDao;
import ru.regionsgroup.inventory.model.Domain;

/**
 * @author mbedritskiy
 */
@Repository
public class HibernateDomainDao extends HibernateGenericDao<Domain, String> implements DomainDao {
    public HibernateDomainDao() {
        super(Domain.class);
    }
}
