package ru.regionsgroup.inventory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.regionsgroup.inventory.config.ApplicationConfig;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.Computer;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.load.PrinterConnectionAuditImport;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Main().doIt();
    }

    public void doIt() {
//        String printerConnectionsLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
//        String activeDirectoryLogRoot = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD\\";
//
//        UserAuditSourceImpl userAuditDirectory = new UserAuditSourceImpl(
//                Paths.get(activeDirectoryLogRoot), "ad_users.json");
//        ComputerAuditSourceImpl computerAuditDirectory = new ComputerAuditSourceImpl(
//                Paths.get(activeDirectoryLogRoot), "ad_computers");
//        DomainAuditSourceImpl domainAuditDirectory = new DomainAuditSourceImpl(
//                Paths.get(activeDirectoryLogRoot), "ad_domains.json");
//        PrinterConnectionAuditSourceImpl printerConnectionAuditDirectory = new PrinterConnectionAuditSourceImpl(
//                Paths.get(printerConnectionsLogRoot), "*.json");
//
//        AuditImport usersLoader = new UserAuditImport(
//                new HibernateUserDao(), new UserAuditConverter(), userAuditDirectory);
//        AuditImport computersLoader = new ComputerAuditImport(
//                new HibernateComputerDao(),
//                new ComputerAuditConverter(),
//                computerAuditDirectory);
//        AuditImport domainsLoader = new DomainAuditImport(
//                new HibernateDomainDao(),
//                new DomainAuditConverter(),
//                domainAuditDirectory);
//        AuditImport printerConnectionsLoader = new PrinterConnectionAuditImport(
//                new HibernatePrinterConnectionDao(),
//                new PrinterConnectionAuditConverter(),
//                printerConnectionAuditDirectory);

        System.out.println("go go go");
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        PrinterConnectionDao prnDao = context.getBean(PrinterConnectionDao.class);
        List<PrinterConnection> result = prnDao.findDefaultsPrinters();
        System.out.println("default printers before:" + result.size());
//        result.forEach(prnDao::delete);

//        AuditMultiLoader auditMultiLoader = context.getBean(AuditMultiLoader.class);
//        auditMultiLoader.run();

        PrinterConnectionAuditImport loader = context.getBean(PrinterConnectionAuditImport.class);
        loader.importToDatabase();

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
