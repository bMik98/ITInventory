package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.config.AuditSourceConfig;
import ru.regionsgroup.inventory.model.Domain;
import ru.regionsgroup.inventory.service.audit.converter.DomainAuditConverter;
import ru.regionsgroup.inventory.service.audit.impl.AbstractAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
@ComponentScan()
public class DomainAuditLoader extends AbstractAuditLoader<Domain> {
    @Autowired
    public DomainAuditLoader(DomainAuditConverter converter, AuditSourceConfig.DomainAuditSource source) {
        super(converter, source);
    }
}
