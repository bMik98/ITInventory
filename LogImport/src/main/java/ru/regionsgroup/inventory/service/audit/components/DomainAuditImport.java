package ru.regionsgroup.inventory.service.audit.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.DomainDao;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.load.DomainAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
public class DomainAuditImport extends AuditSimpleImport implements AuditImport {
    @Autowired
    public DomainAuditImport(DomainDao dao, DomainAuditLoader loader) {
        super(dao, loader);
    }
}
