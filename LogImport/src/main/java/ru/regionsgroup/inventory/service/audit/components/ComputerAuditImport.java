package ru.regionsgroup.inventory.service.audit.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.service.audit.load.ComputerAuditLoader;

/**
 * @author Mbedritskiy
 */
@Component
public class ComputerAuditImport extends AuditSimpleImport {
    @Autowired
    public ComputerAuditImport(ComputerDao dao, ComputerAuditLoader loader) {
        super(dao, loader);
    }
}
