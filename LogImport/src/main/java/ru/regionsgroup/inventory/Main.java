package ru.regionsgroup.inventory;

import org.hibernate.Hibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.regionsgroup.inventory.config.AuditSourceConfig;
import ru.regionsgroup.inventory.dao.ComputerDao;
import ru.regionsgroup.inventory.dao.PrinterConnectionDao;
import ru.regionsgroup.inventory.model.Computer;
import ru.regionsgroup.inventory.model.PrinterConnection;
import ru.regionsgroup.inventory.service.audit.component.PrinterConnectionAuditImport;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Main().doIt();
    }

    public void doIt() {
        System.out.println("go go go");
        ApplicationContext context = new AnnotationConfigApplicationContext(AuditSourceConfig.class);
        PrinterConnectionDao prnDao = context.getBean(PrinterConnectionDao.class);
        List<PrinterConnection> result = prnDao.findDefaultPrinters();
        System.out.println("default printers before:" + result.size());
//        result.forEach(prnDao::delete);

//        AuditMultiLoader auditMultiLoader = context.getBean(AuditMultiLoader.class);
//        auditMultiLoader.run();

        PrinterConnectionAuditImport loader = context.getBean(PrinterConnectionAuditImport.class);
        loader.loadAndSave();

        result = prnDao.findDefaultPrinters();
        System.out.println("default printers after:" + result.size());


        ComputerDao compDao = context.getBean(ComputerDao.class);
        Computer computer = compDao.getById("pc-reg-sysadm4.regions.local");
        int res = prnDao.deleteByComputer(computer);
        System.out.println("deleted " + res);
        Hibernate.initialize(computer.getPrinterConnections());
        System.out.println(computer.getPrinterConnections().size());
        for (PrinterConnection printerConnection : computer.getPrinterConnections()) {
            System.out.println(printerConnection.getCaption());
        }
//
//        HibernateUtil.getSessionFactory().close();
        System.out.println("Done");
    }
}
