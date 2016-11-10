package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.ComputerAuditContent;
import ru.regionsgroup.inventory.service.audit.directory.ComputerAuditLocation;

/**
 * @author Mbedritskiy
 */
public class ComputerAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public ComputerAuditLoader(
            final ComputerDao dao,
            final ComputerAuditContent contentConverter,
            final ComputerAuditLocation auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditLocation(auditDirectory);
    }
}
