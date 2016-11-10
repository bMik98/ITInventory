package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.PrinterConnectionAuditContent;
import ru.regionsgroup.inventory.service.audit.directory.PrinterConnectionAuditLocation;

/**
 * @author Mbedritskiy
 */
public class PrinterConnectionAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public PrinterConnectionAuditLoader(
            final PrinterConnectionDao dao,
            final PrinterConnectionAuditContent contentConverter,
            final PrinterConnectionAuditLocation auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditLocation(auditDirectory);
    }
}
