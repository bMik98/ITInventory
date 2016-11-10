package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContent;
import ru.regionsgroup.inventory.model.Computer;

/**
 * @author Mbedritskiy
 */
public class ComputerAuditContent
        extends JsonAuditContent<Computer>
        implements AuditContent<Computer> {
}
