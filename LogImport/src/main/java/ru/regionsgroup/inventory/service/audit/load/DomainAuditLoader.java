package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.DomainDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.DomainAuditContent;
import ru.regionsgroup.inventory.service.audit.directory.DomainAuditLocation;

/**
 * @author Mbedritskiy
 */
public class DomainAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public DomainAuditLoader(
            final DomainDao dao,
            final DomainAuditContent contentConverter,
            final DomainAuditLocation auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditLocation(auditDirectory);
    }
}
