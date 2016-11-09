package ru.regionsgroup.inventory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.regionsgroup.inventory.config.ApplicationConfig;
import ru.regionsgroup.inventory.dao.impl.HibernateComputerDao;
import ru.regionsgroup.inventory.dao.impl.HibernateDomainDao;
import ru.regionsgroup.inventory.dao.impl.HibernatePrinterConnectionDao;
import ru.regionsgroup.inventory.service.audit.AuditContentConverter;
import ru.regionsgroup.inventory.service.audit.AuditDirectory;
import ru.regionsgroup.inventory.service.audit.converter.ComputerAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.converter.DomainAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.converter.PrinterConnectionAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.converter.UserAuditContentConverter;
import ru.regionsgroup.inventory.service.audit.directory.ComputerAuditDirectory;
import ru.regionsgroup.inventory.service.audit.directory.DomainAuditDirectory;
import ru.regionsgroup.inventory.service.audit.directory.PrinterConnectionAuditDirectory;
import ru.regionsgroup.inventory.service.audit.directory.UserAuditDirectory;
import ru.regionsgroup.inventory.dao.UserDao;
import ru.regionsgroup.inventory.dao.impl.HibernateUserDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.load.ComputerAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.DomainAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.PrinterConnectionAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.UserAuditLoader;
import ru.regionsgroup.inventory.service.utils.HibernateUtil;

import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        new Main().doIt();
    }

    public void doIt() {
//        String printerConnectionsLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
//        String activeDirectoryLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD\\";
//
//        UserAuditDirectory userAuditDirectory = new UserAuditDirectory(
//                Paths.get(activeDirectoryLogRoot), "ad_users.json");
//        ComputerAuditDirectory computerAuditDirectory = new ComputerAuditDirectory(
//                Paths.get(activeDirectoryLogRoot), "ad_computers");
//        DomainAuditDirectory domainAuditDirectory = new DomainAuditDirectory(
//                Paths.get(activeDirectoryLogRoot), "ad_domains.json");
//        PrinterConnectionAuditDirectory printerConnectionAuditDirectory = new PrinterConnectionAuditDirectory(
//                Paths.get(printerConnectionsLogRoot), "*.json");
//
//        AuditLoader usersLoader = new UserAuditLoader(
//                new HibernateUserDao(), new UserAuditContentConverter(), userAuditDirectory);
//        AuditLoader computersLoader = new ComputerAuditLoader(
//                new HibernateComputerDao(),
//                new ComputerAuditContentConverter(),
//                computerAuditDirectory);
//        AuditLoader domainsLoader = new DomainAuditLoader(
//                new HibernateDomainDao(),
//                new DomainAuditContentConverter(),
//                domainAuditDirectory);
//        AuditLoader printerConnectionsLoader = new PrinterConnectionAuditLoader(
//                new HibernatePrinterConnectionDao(),
//                new PrinterConnectionAuditContentConverter(),
//                printerConnectionAuditDirectory);

        System.out.println("go go go");
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println("users");
        UserAuditLoader usersLoader = context.getBean(UserAuditLoader.class);
        usersLoader.importToDatabase();
//        System.out.println("computers");
//        computersLoader.importToDatabase();
//        System.out.println("domains");
//        domainsLoader.importToDatabase();
//        System.out.println("printer connections");
//        printerConnectionsLoader.importToDatabase();
//
        HibernateUtil.getSessionFactory().close();
        System.out.println("Done");
    }
}
