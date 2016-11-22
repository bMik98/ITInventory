package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.DomainDao;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.content.DomainAuditConverter;
import ru.regionsgroup.inventory.service.audit.source.DomainAuditSourceImpl;

/**
 * @author Mbedritskiy
 */
@Component
public class DomainAuditImport extends AuditImportImpl implements AuditImport {

    @Autowired
    public void setDao(DomainDao dao) {
        super.setDao(dao);
    }

    @Autowired
    public void setConverter(DomainAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(DomainAuditSourceImpl location) {
        super.setLocation(location);
    }
}
