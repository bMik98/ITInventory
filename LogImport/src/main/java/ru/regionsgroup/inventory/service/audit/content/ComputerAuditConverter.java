package ru.regionsgroup.inventory.service.audit.content;

import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.model.Computer;

/**
 * @author Mbedritskiy
 */
public class ComputerAuditConverter
        extends JsonAuditConverter<Computer>
        implements AuditConverter<Computer> {
}
