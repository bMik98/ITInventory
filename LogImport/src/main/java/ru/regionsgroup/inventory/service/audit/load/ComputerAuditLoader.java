package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.config.AuditSourceConfig.ComputerAuditSource;
import ru.regionsgroup.inventory.model.Computer;
import ru.regionsgroup.inventory.service.audit.converter.ComputerAuditConverter;
import ru.regionsgroup.inventory.service.audit.impl.AbstractAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
@ComponentScan(basePackages = "ru.regionsgroup.inventory.*")
public class ComputerAuditLoader extends AbstractAuditLoader<Computer> {
    @Autowired
    public ComputerAuditLoader(ComputerAuditConverter converter, ComputerAuditSource source) {
        super(converter, source);
    }
}
