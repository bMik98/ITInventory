package ru.regionsgroup.inventory.dao.impl;

import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.PrinterConnection;

/**
 * @author Mbedritskiy
 */
public class HibernatePrinterConnectionDao
        extends HibernateGenericDao<PrinterConnection, Long> implements PrinterConnectionDao {
    public HibernatePrinterConnectionDao() {
        super(PrinterConnection.class);
    }
}
