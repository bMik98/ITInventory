package ru.regionsgroup.inventory.service.audit.converter;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.model.Computer;

/**
 * @author Mbedritskiy
 */
@Component
public class ComputerAuditConverter extends JsonAuditConverter<Computer> {
    public ComputerAuditConverter() {
        super(Computer.class);
    }
}
