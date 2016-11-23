package ru.regionsgroup.inventory.service.audit.components;

import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.dao.GenericDao;

/**
 * @author Mbedritskiy
 */
public abstract class AuditSimpleImport implements AuditImport {
    private final GenericDao dao;
    private final AuditLoader loader;

    public AuditSimpleImport(GenericDao dao, AuditLoader loader) {
        this.dao = dao;
        this.loader = loader;
    }

    @Override
    public void loadAndSave() {
        getDao().saveOrUpdate(getLoader().load());
    }

    public GenericDao getDao() {
        return dao;
    }

    public AuditLoader getLoader() {
        return loader;
    }
}
