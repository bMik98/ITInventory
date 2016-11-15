package ru.regionsgroup.inventory.service.audit.content;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.service.audit.AuditConverter;
import ru.regionsgroup.inventory.model.User;

/**
 * @author Mbedritskiy
 */
@Component
public class UserAuditConverter
        extends JsonAuditConverter<User>
        implements AuditConverter<User> {
}
