package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.content.ComputerAuditConverter;
import ru.regionsgroup.inventory.service.audit.source.ComputerAuditSourceImpl;

/**
 * @author Mbedritskiy
 */
@Component
public class ComputerAuditImport extends AuditImportImpl implements AuditImport {

    @Autowired
    public void setDao(ComputerDao dao) {
        super.setDao(dao);
    }

    @Autowired
    public void setConverter(ComputerAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(ComputerAuditSourceImpl location) {
        super.setLocation(location);
    }
}
