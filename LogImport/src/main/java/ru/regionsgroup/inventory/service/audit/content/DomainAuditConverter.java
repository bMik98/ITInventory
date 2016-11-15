package ru.regionsgroup.inventory.service.audit.content;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.model.Domain;

/**
 * @author Mbedritskiy
 */
@Component
public class DomainAuditConverter
        extends JsonAuditConverter<Domain>
        implements AuditConverter<Domain> {
}

