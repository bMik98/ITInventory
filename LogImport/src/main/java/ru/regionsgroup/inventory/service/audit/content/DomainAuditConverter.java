package ru.regionsgroup.inventory.service.audit.content;

import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.model.Domain;

/**
 * @author Mbedritskiy
 */
public class DomainAuditConverter
        extends JsonAuditConverter<Domain>
        implements AuditConverter<Domain> {
}

