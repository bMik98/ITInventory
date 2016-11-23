package ru.regionsgroup.inventory.service.audit.converter;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.model.User;

/**
 * @author Mbedritskiy
 */
@Component
public class UserAuditConverter extends JsonAuditConverter<User> {
    public UserAuditConverter() {
        super(User.class);
    }
}
