package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContent;
import ru.regionsgroup.inventory.model.PrinterConnection;

/**
 * @author Mbedritskiy
 */
public class PrinterConnectionAuditContent
        extends JsonAuditContent<PrinterConnection>
        implements AuditContent<PrinterConnection> {
}
