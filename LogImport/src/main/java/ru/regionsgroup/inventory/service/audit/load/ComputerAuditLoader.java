package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.ComputerAuditConverter;
import ru.regionsgroup.inventory.service.audit.location.ComputerAuditLocation;

/**
 * @author Mbedritskiy
 */
public class ComputerAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public ComputerAuditLoader(
            final ComputerDao dao,
            final ComputerAuditConverter contentConverter,
            final ComputerAuditLocation auditDirectory) {
        super();
        setDao(dao);
        setConverter(contentConverter);
        setLocation(auditDirectory);
    }
}
