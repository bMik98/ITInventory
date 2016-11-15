package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.DomainDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.DomainAuditConverter;
import ru.regionsgroup.inventory.service.audit.location.DomainAuditLocation;

/**
 * @author Mbedritskiy
 */
@Component
public class DomainAuditLoader extends AuditLoaderImpl implements AuditLoader {

    @Autowired
    public void setDao(DomainDao dao) {
        super.setDao(dao);
    }

    @Autowired
    public void setConverter(DomainAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(DomainAuditLocation location) {
        super.setLocation(location);
    }
}
