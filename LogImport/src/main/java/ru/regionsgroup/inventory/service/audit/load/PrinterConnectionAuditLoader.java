package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.config.AuditSourceConfig.PrinterConnectionAuditSource;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.converter.PrinterConnectionAuditConverter;
import ru.regionsgroup.inventory.service.audit.impl.AbstractAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
public class PrinterConnectionAuditLoader extends AbstractAuditLoader<PrinterConnection> {
    @Autowired
    public PrinterConnectionAuditLoader(
            PrinterConnectionAuditConverter converter, PrinterConnectionAuditSource source) {
        super(converter, source);
    }
}
