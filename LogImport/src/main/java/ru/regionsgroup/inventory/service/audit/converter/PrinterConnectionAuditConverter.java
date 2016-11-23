package ru.regionsgroup.inventory.service.audit.converter;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.model.PrinterConnection;

/**
 * @author Mbedritskiy
 */
@Component
public class PrinterConnectionAuditConverter extends JsonAuditConverter<PrinterConnection> {
    public PrinterConnectionAuditConverter() {
        super(PrinterConnection.class);
    }
}
