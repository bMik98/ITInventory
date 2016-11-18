package ru.regionsgroup.inventory.service.audit.content;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.model.InstalledSoftware;
import ru.regionsgroup.inventory.service.audit.AuditConverter;

/**
 * @author Mbedritskiy
 */
@Component
public class InstalledSoftwareAuditConverter
        extends JsonAuditConverter<InstalledSoftware>
        implements AuditConverter<InstalledSoftware> {
}
