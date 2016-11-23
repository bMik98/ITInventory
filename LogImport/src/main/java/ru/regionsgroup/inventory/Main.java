package ru.regionsgroup.inventory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.regionsgroup.inventory.config.AuditSourceConfig;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.Computer;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.components.PrinterConnectionAuditImport;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Main().doIt();
    }

    public void doIt() {
//        String printerConnectionsLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
//        String activeDirectoryLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD\\";
//
//        UserAuditFileSource userAuditDirectory = new UserAuditFileSource(
//                Paths.get(activeDirectoryLogRoot), "ad_users.json");
//        ComputerAuditFileSource computerAuditDirectory = new ComputerAuditFileSource(
//                Paths.get(activeDirectoryLogRoot), "ad_computers");
//        DomainAuditFileSource domainAuditDirectory = new DomainAuditFileSource(
//                Paths.get(activeDirectoryLogRoot), "ad_domains.json");
//        PrinterConnectionAuditFileSource printerConnectionAuditDirectory = new PrinterConnectionAuditFileSource(
//                Paths.get(printerConnectionsLogRoot), "*.json");
//
//        AuditImport usersLoader = new UserAuditSimpleImport(
//                new HibernateUserDao(), new UserAuditConverter(), userAuditDirectory);
//        AuditImport computersLoader = new ComputerAuditBasicImport(
//                new HibernateComputerDao(),
//                new ComputerAuditConverter(),
//                computerAuditDirectory);
//        AuditImport domainsLoader = new DomainAuditBasicImport(
//                new HibernateDomainDao(),
//                new DomainAuditConverter(),
//                domainAuditDirectory);
//        AuditImport printerConnectionsLoader = new PrinterConnectionAuditSimpleImport(
//                new HibernatePrinterConnectionDao(),
//                new PrinterConnectionAuditConverter(),
//                printerConnectionAuditDirectory);

        System.out.println("go go go");
        ApplicationContext context = new AnnotationConfigApplicationContext(AuditSourceConfig.class);
        PrinterConnectionDao prnDao = context.getBean(PrinterConnectionDao.class);
        List<PrinterConnection> result = prnDao.findDefaultsPrinters();
        System.out.println("default printers before:" + result.size());
//        result.forEach(prnDao::delete);

//        AuditMultiLoader auditMultiLoader = context.getBean(AuditMultiLoader.class);
//        auditMultiLoader.run();

        PrinterConnectionAuditImport loader = context.getBean(PrinterConnectionAuditImport.class);
        loader.loadAndSave();

        result = prnDao.findDefaultsPrinters();
        System.out.println("default printers after:" + result.size());


        ComputerDao compDao = context.getBean(ComputerDao.class);
        Computer computer = compDao.getById("pc-reg-sysadm4.regions.local");
//        Hibernate.initialize(computer.getPrinterConnections());
        System.out.println(computer.getPrinterConnections().size());
        for (PrinterConnection printerConnection : computer.getPrinterConnections()) {
            System.out.println(printerConnection.getCaption());
        }
//
//        HibernateUtil.getSessionFactory().close();
        System.out.println("Done");
    }
}
