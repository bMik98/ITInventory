package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.UserAuditConverter;
import ru.regionsgroup.inventory.service.audit.location.UserAuditLocation;

/**
 * @author Mbedritskiy
 */
@Component
public class UserAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public UserAuditLoader(
            final UserDao dao,
            final UserAuditConverter contentConverter,
            final UserAuditLocation auditDirectory) {
        super();
        setDao(dao);
        setConverter(contentConverter);
        setLocation(auditDirectory);
    }
}
