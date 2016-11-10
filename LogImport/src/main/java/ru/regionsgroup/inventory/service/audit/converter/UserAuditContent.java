package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContent;
import ru.regionsgroup.inventory.model.User;

/**
 * @author Mbedritskiy
 */
public class UserAuditContent
        extends JsonAuditContent<User>
        implements AuditContent<User> {
}
