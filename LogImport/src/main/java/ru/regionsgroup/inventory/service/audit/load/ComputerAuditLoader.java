package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.ComputerAuditConverter;
import ru.regionsgroup.inventory.service.audit.location.ComputerAuditLocation;

/**
 * @author Mbedritskiy
 */
@Component
public class ComputerAuditLoader extends AuditLoaderImpl implements AuditLoader {

    @Autowired
    public void setDao(ComputerDao dao) {
        super.setDao(dao);
    }

    @Autowired
    public void setConverter(ComputerAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(ComputerAuditLocation location) {
        super.setLocation(location);
    }
}
