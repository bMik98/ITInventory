package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.content.PrinterConnectionAuditConverter;
import ru.regionsgroup.inventory.service.audit.source.PrinterConnectionAuditSourceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@Component
public class PrinterConnectionAuditImport extends AuditImportImpl implements AuditImport {

    @Autowired
    private ComputerDao compDao;

    @Override
    public int importToDatabase() {
        deleteAll();
        List<String> content = getLocation().importContent();
        List entries = getConverter().decodeArrays(content);
        List<PrinterConnection> validEntries = new ArrayList<>();
        for (Object object : entries) {
            PrinterConnection printer = (PrinterConnection) object;
            if (compDao.getById(printer.getComputerId()) != null) {
                validEntries.add(printer);
            }
        }
        getDao().saveOrUpdate(validEntries);
        return entries.size();
    }

    public void deleteAll() {
        PrinterConnectionDao prnDao = (PrinterConnectionDao) getDao();
        List<PrinterConnection> result = prnDao.getAll();
        result.forEach(prnDao::delete);
    }

    @Autowired
    public void setConverter(PrinterConnectionAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(PrinterConnectionAuditSourceImpl location) {
        super.setLocation(location);
    }

    @Autowired
    public void setDao(PrinterConnectionDao dao) {
        super.setDao(dao);
    }
}
