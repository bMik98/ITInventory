package ru.regionsgroup.inventory.service.audit.content;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.model.Computer;

/**
 * @author Mbedritskiy
 */
@Component
public class ComputerAuditConverter
        extends JsonAuditConverter<Computer>
        implements AuditConverter<Computer> {
}
