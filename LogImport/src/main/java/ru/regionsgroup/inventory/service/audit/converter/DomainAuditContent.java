package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContent;
import ru.regionsgroup.inventory.model.Domain;

/**
 * @author Mbedritskiy
 */
public class DomainAuditContent
        extends JsonAuditContent<Domain>
        implements AuditContent<Domain> {
}

