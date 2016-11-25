package ru.regionsgroup.inventory.dao;

import ru.regionsgroup.inventory.model.Computer;
import ru.regionsgroup.inventory.model.PrinterConnection;

import java.util.List;

/**
 * @author mbedritskiy
 */
public interface PrinterConnectionDao extends GenericComputerDependedDao<PrinterConnection, Long> {
    List<PrinterConnection> findDefaultPrinters();
}
