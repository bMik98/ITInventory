package ru.regionsgroup.inventory.dao.impl;

import org.springframework.stereotype.Repository;
import ru.regionsgroup.inventory.dao.InstalledSoftwareDao;
import ru.regionsgroup.inventory.model.InstalledSoftware;

/**
 * @author Mbedritskiy
 */
@Repository
public class HibernateInstalledSoftwareDao
        extends HibernateGenericDao<InstalledSoftware, Long>
        implements InstalledSoftwareDao {
    public HibernateInstalledSoftwareDao() {
        super(InstalledSoftware.class);
    }
}
