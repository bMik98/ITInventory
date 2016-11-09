package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.UserAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.directory.UserAuditDirectory;

/**
 * @author Mbedritskiy
 */
public class UserAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public UserAuditLoader(
            final UserDao dao,
            final UserAuditContentConverter contentConverter,
            final UserAuditDirectory auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditDirectory(auditDirectory);
    }
}
