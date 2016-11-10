package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.UserAuditContent;
import ru.regionsgroup.inventory.service.audit.directory.UserAuditLocation;

/**
 * @author Mbedritskiy
 */
public class UserAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public UserAuditLoader(
            final UserDao dao,
            final UserAuditContent contentConverter,
            final UserAuditLocation auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditLocation(auditDirectory);
    }
}
