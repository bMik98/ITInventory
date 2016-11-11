package ru.regionsgroup.inventory.service.audit.content;

import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.model.User;

/**
 * @author Mbedritskiy
 */
public class UserAuditConverter
        extends JsonAuditConverter<User>
        implements AuditConverter<User> {
}
