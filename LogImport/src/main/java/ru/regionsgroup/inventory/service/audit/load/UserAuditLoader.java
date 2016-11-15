package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public void setDao(UserDao dao) {
        super.setDao(dao);
    }

    @Autowired
    public void setConverter(UserAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(UserAuditLocation location) {
        super.setLocation(location);
    }
}
