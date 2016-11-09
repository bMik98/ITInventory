package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContentConverter;
import ru.regionsgroup.inventory.model.Domain;

/**
 * @author Mbedritskiy
 */
public class DomainAuditContentConverter
        extends JsonAuditContentConverter<Domain>
        implements AuditContentConverter<Domain> {
}

