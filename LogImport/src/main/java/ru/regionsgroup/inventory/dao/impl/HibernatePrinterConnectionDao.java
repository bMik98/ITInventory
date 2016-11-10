package ru.regionsgroup.inventory.dao.impl;

import org.springframework.stereotype.Repository;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.PrinterConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@Repository
public class HibernatePrinterConnectionDao
        extends HibernateGenericDao<PrinterConnection, Long> implements PrinterConnectionDao {
    public HibernatePrinterConnectionDao() {
        super(PrinterConnection.class);
    }

    @Override
    public List<PrinterConnection> findDefaultsPrinters() {
        List<PrinterConnection> result = new ArrayList<>();
        getSession().beginTransaction();
        result = getSession().createQuery("from PrinterConnection c where c.asDefault = true", PrinterConnection.class).list();
        getSession().getTransaction().commit();
        return result;
    }
}
