package ru.regionsgroup.inventory.service.audit.converter;

import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.model.InstalledSoftware;

/**
 * @author Mbedritskiy
 */
@Component
public class InstalledSoftwareAuditConverter extends JsonAuditConverter<InstalledSoftware> {
    public InstalledSoftwareAuditConverter() {
        super(InstalledSoftware.class);
    }
}
