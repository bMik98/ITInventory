package ru.regionsgroup.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.regionsgroup.inventory.dao.impl.HibernateComputerDao;
import ru.regionsgroup.inventory.dao.impl.HibernateDomainDao;
import ru.regionsgroup.inventory.dao.impl.HibernatePrinterConnectionDao;
import ru.regionsgroup.inventory.dao.impl.HibernateUserDao;
import ru.regionsgroup.inventory.service.audit.AuditLoader;
import ru.regionsgroup.inventory.service.audit.content.*;
import ru.regionsgroup.inventory.service.audit.load.*;
import ru.regionsgroup.inventory.service.audit.location.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mbedritskiy
 */
@Configuration
@ComponentScan({"ru.regionsgroup.inventory.dao.impl", "ru.regionsgroup.inventory.service.audit.*"})
public class ApplicationConfig {
    private final String PRN_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-Printers";
    private final String AD_ROOT = "\\\\netapp2.regions.local\\Distibutive\\_Logs\\Log-AD";
    private final String COMP_LOG = "ad_computers.json";
    private final String USER_LOG = "ad_users.json";
    private final String DOMAIN_LOG = "ad_domains.json";
    private final String LOG_FILTER = "*.json";

    private HibernateUserDao userDao;
    private HibernateComputerDao computerDao;
    private HibernateDomainDao domainDao;
    private HibernatePrinterConnectionDao printerConnectionDao;

    @Bean
    UserAuditLocation userAuditLocation() {
        return new UserAuditLocation(Paths.get(AD_ROOT), USER_LOG);
    }

    @Bean
    ComputerAuditLocation computerAuditLocation() {
        return new ComputerAuditLocation(Paths.get(AD_ROOT), COMP_LOG);
    }

    @Bean
    DomainAuditLocation domainAuditLocation() {
        return new DomainAuditLocation(Paths.get(AD_ROOT), DOMAIN_LOG);
    }

    @Bean
    PrinterConnectionAuditLocation printerConnectionAuditLocation() {
        return new PrinterConnectionAuditLocation(Paths.get(PRN_ROOT), LOG_FILTER);
    }

    @Bean
    UserAuditConverter userAuditConverter() {
        return new UserAuditConverter();
    }

    @Bean
    ComputerAuditConverter computerAuditConverter() {
        return new ComputerAuditConverter();
    }

    @Bean
    DomainAuditConverter domainAuditConverter() {
        return new DomainAuditConverter();
    }

    @Bean
    PrinterConnectionAuditConverter printerConnectionAuditConverter() {
        return new PrinterConnectionAuditConverter();
    }

    @Bean
    UserAuditLoader userAuditLoader() {
        return new UserAuditLoader(userDao, userAuditConverter(), userAuditLocation());
    }

    @Bean
    ComputerAuditLoader computerAuditLoader() {
        return new ComputerAuditLoader(computerDao, computerAuditConverter(), computerAuditLocation());
    }

    @Bean
    DomainAuditLoader domainAuditLoader() {
        return new DomainAuditLoader(domainDao, domainAuditConverter(), domainAuditLocation());
    }

//    @Bean
//    PrinterConnectionAuditLoader printerConnectionAuditLoader() {
//        return new PrinterConnectionAuditLoader(
//                printerConnectionDao, printerConnectionAuditContent(), printerConnectionAuditLocation());
//    }

//    @Bean
//    AuditMultiLoader auditImport() {
//        AuditMultiLoader batch = new AuditMultiLoader();
//        batch.addLoader(domainAuditLoader());
//        batch.addLoader(userAuditLoader());
//        batch.addLoader(computerAuditLoader());
//        batch.addLoader(printerConnectionAuditLoader);
//        return batch;
//    }

    @Bean
    List<AuditLoader> someAuditLoaders() {
        List<AuditLoader> result = new ArrayList<>();
        result.add(computerAuditLoader());
        return result;
    }

    @Autowired
    public void setUserDao(HibernateUserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setComputerDao(HibernateComputerDao computerDao) {
        this.computerDao = computerDao;
    }

    @Autowired
    public void setDomainDao(HibernateDomainDao domainDao) {
        this.domainDao = domainDao;
    }

    @Autowired
    public void setPrinterConnectionDao(HibernatePrinterConnectionDao printerConnectionDao) {
        this.printerConnectionDao = printerConnectionDao;
    }
}
