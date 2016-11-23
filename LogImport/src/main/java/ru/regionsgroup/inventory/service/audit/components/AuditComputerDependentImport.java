package ru.regionsgroup.inventory.service.audit.components;

import org.springframework.beans.factory.annotation.Autowired;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.dao.GenericDao;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.AuditLoader;

/**
 * @author Mbedritskiy
 */
public abstract class AuditComputerDependentImport extends AuditSimpleImport implements AuditImport {
    private ComputerDao computerDao;

    public AuditComputerDependentImport(final GenericDao dao, final AuditLoader loader) {
        super(dao, loader);
    }

    @Autowired
    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }
}
