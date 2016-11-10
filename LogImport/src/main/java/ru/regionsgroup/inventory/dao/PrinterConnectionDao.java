package ru.regionsgroup.inventory.dao;

import ru.regionsgroup.inventory.model.PrinterConnection;

import java.util.List;

/**
 * @author mbedritskiy
 */
public interface PrinterConnectionDao extends GenericDao<PrinterConnection, Long> {
    List<PrinterConnection> findDefaultsPrinters();
}
