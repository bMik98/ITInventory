package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContentConverter;
import ru.regionsgroup.inventory.model.Computer;

/**
 * @author Mbedritskiy
 */
public class ComputerAuditContentConverter
        extends JsonAuditContentConverter<Computer>
        implements AuditContentConverter<Computer> {
}
