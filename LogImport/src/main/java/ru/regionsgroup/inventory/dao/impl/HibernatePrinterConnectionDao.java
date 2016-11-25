package ru.regionsgroup.inventory.dao.impl;

import org.springframework.core.env.CommandLinePropertySource;
import org.springframework.stereotype.Repository;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.Computer;
import ru.regionsgroup.inventory.model.PrinterConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Mbedritskiy
 */
@Repository
public class HibernatePrinterConnectionDao
        extends HibernateGenericComputerDependedDao<PrinterConnection, Long>
        implements PrinterConnectionDao {
    public HibernatePrinterConnectionDao() {
        super(PrinterConnection.class);
    }

    @Override
    public List<PrinterConnection> findDefaultPrinters() {
        return getSession()
                .createQuery("from PrinterConnection c where c.asDefault = true", PrinterConnection.class)
                .list();
    }
}
