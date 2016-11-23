package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.config.AuditSourceConfig;
import ru.regionsgroup.inventory.model.User;
import ru.regionsgroup.inventory.service.audit.converter.UserAuditConverter;
import ru.regionsgroup.inventory.service.audit.impl.AbstractAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
public class UserAuditLoader extends AbstractAuditLoader<User> {
    @Autowired
    public UserAuditLoader(UserAuditConverter converter, AuditSourceConfig.UserAuditSource source) {
        super(converter, source);
    }
}
