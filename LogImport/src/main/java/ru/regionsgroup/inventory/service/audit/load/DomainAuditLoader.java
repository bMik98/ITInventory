package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.DomainDao;
import ru.regionsgroup.inventory.service.audit.AuditContentConverter;
import ru.regionsgroup.inventory.service.audit.AuditDirectory;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.DomainAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.directory.DomainAuditDirectory;

/**
 * @author Mbedritskiy
 */
public class DomainAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public DomainAuditLoader(
            final DomainDao dao,
            final DomainAuditContentConverter contentConverter,
            final DomainAuditDirectory auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditDirectory(auditDirectory);
    }
}
