package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.config.AuditSourceConfig.InstalledSoftwareAuditSource;
import ru.regionsgroup.inventory.model.InstalledSoftware;
import ru.regionsgroup.inventory.service.audit.converter.InstalledSoftwareAuditConverter;
import ru.regionsgroup.inventory.service.audit.impl.AbstractAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
public class InstalledSoftwareAuditLoader extends AbstractAuditLoader<InstalledSoftware> {
    @Autowired
    public InstalledSoftwareAuditLoader(
            InstalledSoftwareAuditConverter converter, InstalledSoftwareAuditSource source) {
        super(converter, source);
    }
}
