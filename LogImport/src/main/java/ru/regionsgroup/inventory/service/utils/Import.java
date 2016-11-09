package ru.regionsgroup.inventory.service.utils;

import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.dao.impl.HibernatePrinterConnectionDao;
import ru.regionsgroup.inventory.dao.impl.HibernateUserDao;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.model.User;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author Mbedritskiy
 */
public class Import {
    public static int usersFromLogFile(final String fileSpec) {
        List<User> users = JsonUtils.decodeFile(Paths.get(fileSpec), "windows-1251", User.class);
        UserDao dao = new HibernateUserDao();
        for (User user : users) {
            dao.saveOrUpdate(user);
        }
        return users.size();
    }

    public static int printerConnectionsFromLogFile(final String fileSpec) {
        List<PrinterConnection> connections = JsonUtils.decodeFile(
                Paths.get(fileSpec),
                "UTF-16LE",
                PrinterConnection.class);
        PrinterConnectionDao dao = new HibernatePrinterConnectionDao();
        for (PrinterConnection  connection : connections) {
            dao.saveOrUpdate(connection);
        }
        return connections.size();
    }
}
