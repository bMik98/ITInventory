package ru.regionsgroup.inventory.service.audit.load;

import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.converter.PrinterConnectionAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.directory.PrinterConnectionAuditDirectory;

/**
 * @author Mbedritskiy
 */
public class PrinterConnectionAuditLoader extends AuditLoaderImpl implements AuditLoader {
    public PrinterConnectionAuditLoader(
            final PrinterConnectionDao dao,
            final PrinterConnectionAuditContentConverter contentConverter,
            final PrinterConnectionAuditDirectory auditDirectory) {
        super();
        setDao(dao);
        setContentConverter(contentConverter);
        setAuditDirectory(auditDirectory);
    }
}
