package ru.regionsgroup.inventory.service.audit.converter;

import ru.regionsgroup.inventory.service.audit.AuditContentConverter;
import ru.regionsgroup.inventory.model.User;

/**
 * @author Mbedritskiy
 */
public class UserAuditContentConverter
        extends JsonAuditContentConverter<User>
        implements AuditContentConverter<User> {
}
