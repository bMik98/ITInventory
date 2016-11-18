package ru.regionsgroup.inventory.service.audit.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.dao.impl.HibernatePrinterConnectionDao;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.PrinterConnectionAuditConverter;
import ru.regionsgroup.inventory.service.audit.location.PrinterConnectionAuditLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@Component
public class PrinterConnectionAuditLoader extends AuditLoaderImpl implements AuditLoader {

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
        List<PrinterConnection> result = prnDao.findDefaultsPrinters();
        System.out.println(result.size());
        result.forEach(prnDao::delete);
    }

    @Autowired
    public void setConverter(PrinterConnectionAuditConverter converter) {
        super.setConverter(converter);
    }

    @Autowired
    public void setAuditLocation(PrinterConnectionAuditLocation location) {
        super.setLocation(location);
    }

    @Autowired
    public void setDao(PrinterConnectionDao dao) {
        super.setDao(dao);
    }
}
