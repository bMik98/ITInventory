package ru.regionsgroup.inventory.service.audit.content;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.model.PrinterConnection;

/**
 * @author Mbedritskiy
 */
@Component
public class PrinterConnectionAuditConverter
        extends JsonAuditConverter<PrinterConnection>
        implements AuditConverter<PrinterConnection> {
}
