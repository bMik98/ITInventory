package ru.regionsgroup.inventory.service.audit.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.GenericDao;
import ru.regionsgroup.inventory.dao.InstalledSoftwareDao;
import ru.regionsgroup.inventory.model.InstalledSoftware;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.load.InstalledSoftwareAuditLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@Component
public class InstalledSoftwareAuditImport extends AuditComputerDependentImport {
    @Autowired
    public InstalledSoftwareAuditImport(InstalledSoftwareDao dao, InstalledSoftwareAuditLoader loader) {
        super(dao, loader);
    }

    @Override
    public void loadAndSave() {
        deleteAll();
        List entries = getLoader().load();
        List<InstalledSoftware> validEntries = new ArrayList<>();
        for (Object object : entries) {
            InstalledSoftware printer = (InstalledSoftware) object;
            if (getComputerDao().getById(printer.getComputerId()) != null) {
                validEntries.add(printer);
            }
        }
        getDao().saveOrUpdate(validEntries);
    }

    public void deleteAll() {
        InstalledSoftwareDao prnDao = (InstalledSoftwareDao) getDao();
        List<InstalledSoftware> result = prnDao.getAll();
        result.forEach(prnDao::delete);
    }
}
