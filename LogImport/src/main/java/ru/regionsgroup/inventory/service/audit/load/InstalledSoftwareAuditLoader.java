package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.dao.InstalledSoftwareDao;
import ru.regionsgroup.inventory.dao.impl.HibernateInstalledSoftwareDao;
import ru.regionsgroup.inventory.model.InstalledSoftware;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.InstalledSoftwareAuditConverter;
import ru.regionsgroup.inventory.service.audit.location.InstalledSoftwareAuditLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@Component
public class InstalledSoftwareAuditLoader extends AuditLoaderImpl implements AuditLoader {

    @Autowired
    private ComputerDao compDao;

//    @Autowired
//    private InstalledSoftwareDao softDao;

    @Override
    public int importToDatabase() {
        deleteAll();
        List<String> content = getLocation().importContent();
        List entries = getConverter().decodeArrays(content);
        List<InstalledSoftware> validEntries = new ArrayList<>();
        for (Object object : entries) {
            InstalledSoftware software = (InstalledSoftware) object;
            if (compDao.getById(software.getComputerId()) != null) {
                validEntries.add(software);
            }
        }
        System.out.printf("import %d%n", validEntries.size());
        super.getDao().saveOrUpdate(validEntries);
        return entries.size();
    }

    public void deleteAll() {
        InstalledSoftwareDao softDao = (InstalledSoftwareDao) getDao();
        List<InstalledSoftware> result = softDao.getAll();
        System.out.println(result.size());
        result.forEach(softDao::delete);
    }

    @Autowired
    public void setConverter(InstalledSoftwareAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(InstalledSoftwareAuditLocation location) {
        super.setLocation(location);
    }

    @Autowired
    public void setDao(InstalledSoftwareDao dao) {
        super.setDao(dao);
    }
}
