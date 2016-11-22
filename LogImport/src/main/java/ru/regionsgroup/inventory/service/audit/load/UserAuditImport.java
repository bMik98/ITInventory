package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.content.UserAuditConverter;
import ru.regionsgroup.inventory.service.audit.source.UserAuditSourceImpl;

/**
 * @author Mbedritskiy
 */
@Component
public class UserAuditImport extends AuditImportImpl implements AuditImport {

    @Autowired
    public void setDao(UserDao dao) {
        super.setDao(dao);
    }

    @Autowired
    public void setConverter(UserAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(UserAuditSourceImpl location) {
        super.setLocation(location);
    }
}
