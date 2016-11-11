package ru.regionsgroup.inventory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.regionsgroup.inventory.config.ApplicationConfig;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.AuditMultiLoader;
import ru.regionsgroup.inventory.service.utils.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Main().doIt();
    }

    public void doIt() {
//        String printerConnectionsLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
//        String activeDirectoryLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD\\";
//
//        UserAuditLocation userAuditDirectory = new UserAuditLocation(
//                Paths.get(activeDirectoryLogRoot), "ad_users.json");
//        ComputerAuditLocation computerAuditDirectory = new ComputerAuditLocation(
//                Paths.get(activeDirectoryLogRoot), "ad_computers");
//        DomainAuditLocation domainAuditDirectory = new DomainAuditLocation(
//                Paths.get(activeDirectoryLogRoot), "ad_domains.json");
//        PrinterConnectionAuditLocation printerConnectionAuditDirectory = new PrinterConnectionAuditLocation(
//                Paths.get(printerConnectionsLogRoot), "*.json");
//
//        AuditLoader usersLoader = new UserAuditLoader(
//                new HibernateUserDao(), new UserAuditConverter(), userAuditDirectory);
//        AuditLoader computersLoader = new ComputerAuditLoader(
//                new HibernateComputerDao(),
//                new ComputerAuditConverter(),
//                computerAuditDirectory);
//        AuditLoader domainsLoader = new DomainAuditLoader(
//                new HibernateDomainDao(),
//                new DomainAuditConverter(),
//                domainAuditDirectory);
//        AuditLoader printerConnectionsLoader = new PrinterConnectionAuditLoader(
//                new HibernatePrinterConnectionDao(),
//                new PrinterConnectionAuditConverter(),
//                printerConnectionAuditDirectory);

        System.out.println("go go go");
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        AuditMultiLoader auditMultiLoader = context.getBean(AuditMultiLoader.class);
        auditMultiLoader.run();
        PrinterConnectionDao prnDao = context.getBean(PrinterConnectionDao.class);
        List<PrinterConnection> result = prnDao.findDefaultsPrinters();
        System.out.println(result.size());
//        AuditMultiLoader auditMultiLoader = context.getBean(AuditMultiLoader.class);
//        auditMultiLoader.run();
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
