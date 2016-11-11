package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.impl.HibernatePrinterConnectionDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.PrinterConnectionAuditConverter;
import ru.regionsgroup.inventory.service.audit.location.PrinterConnectionAuditLocation;

/**
 * @author Mbedritskiy
 */
@Component
public class PrinterConnectionAuditLoader extends AuditLoaderImpl implements AuditLoader {

    @Autowired
    public void setDao(HibernatePrinterConnectionDao dao) {
        super.setDao(dao);
    }

    @Autowired
    public void setConverter(PrinterConnectionAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(PrinterConnectionAuditLocation location) {
        super.setLocation(location);
    }
}
