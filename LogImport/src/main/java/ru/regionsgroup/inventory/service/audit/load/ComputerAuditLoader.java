package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.ComputerAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.directory.ComputerAuditDirectory;

/**
 * @author Mbedritskiy
 */
public class ComputerAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public ComputerAuditLoader(
            final ComputerDao dao,
            final ComputerAuditContentConverter contentConverter,
            final ComputerAuditDirectory auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditDirectory(auditDirectory);
    }
}
