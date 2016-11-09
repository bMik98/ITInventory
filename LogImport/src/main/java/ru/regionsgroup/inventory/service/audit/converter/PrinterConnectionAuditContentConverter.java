package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContentConverter;
import ru.regionsgroup.inventory.model.PrinterConnection;

/**
 * @author Mbedritskiy
 */
public class PrinterConnectionAuditContentConverter
        extends JsonAuditContentConverter<PrinterConnection>
        implements AuditContentConverter<PrinterConnection> {
}
