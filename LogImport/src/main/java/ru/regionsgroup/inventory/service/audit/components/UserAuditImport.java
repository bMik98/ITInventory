package ru.regionsgroup.inventory.service.audit.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.load.UserAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
public class UserAuditImport extends AuditSimpleImport implements AuditImport {
    @Autowired
    public UserAuditImport(UserDao dao, UserAuditLoader loader) {
        super(dao, loader);
    }
}
