package ru.regionsgroup.inventory.service.audit.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.load.PrinterConnectionAuditLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@Component
public class PrinterConnectionAuditImport extends AuditComputerDependentImport {
    @Autowired
    public PrinterConnectionAuditImport(PrinterConnectionDao dao, PrinterConnectionAuditLoader loader) {
        super(dao, loader);
    }

    @Override
    public void loadAndSave() {
        deleteAll();
        List entries = getLoader().load();
        List<PrinterConnection> validEntries = new ArrayList<>();
        for (Object object : entries) {
            PrinterConnection printer = (PrinterConnection) object;
            if (getComputerDao().getById(printer.getComputerId()) != null) {
                validEntries.add(printer);
            }
        }
        getDao().saveOrUpdate(validEntries);
    }

    public void deleteAll() {
        PrinterConnectionDao prnDao = (PrinterConnectionDao) getDao();
        List<PrinterConnection> result = prnDao.getAll();
        result.forEach(prnDao::delete);
    }
}
