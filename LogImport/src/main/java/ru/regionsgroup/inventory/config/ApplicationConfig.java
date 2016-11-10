package ru.regionsgroup.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.regionsgroup.inventory.dao.impl.HibernateComputerDao;
import ru.regionsgroup.inventory.dao.impl.HibernateDomainDao;
import ru.regionsgroup.inventory.dao.impl.HibernatePrinterConnectionDao;
import ru.regionsgroup.inventory.dao.impl.HibernateUserDao;
import ru.regionsgroup.inventory.service.audit.AuditImport;
import ru.regionsgroup.inventory.service.audit.converter.*;
import ru.regionsgroup.inventory.service.audit.converter.ComputerAuditContent;
import ru.regionsgroup.inventory.service.audit.converter.DomainAuditContent;
import ru.regionsgroup.inventory.service.audit.directory.*;
import ru.regionsgroup.inventory.service.audit.directory.DomainAuditLocation;
import ru.regionsgroup.inventory.service.audit.directory.UserAuditLocation;
import ru.regionsgroup.inventory.service.audit.load.ComputerAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.DomainAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.PrinterConnectionAuditLoader;
import ru.regionsgroup.inventory.service.audit.load.UserAuditLoader;

import java.nio.file.Paths;

/**
 * @author Mbedritskiy
 */
@Configuration
@ComponentScan({"ru.regionsgroup.inventory.dao.impl"})
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
    UserAuditLocation userAuditDirectory() {
        return new UserAuditLocation(Paths.get(AD_ROOT), USER_LOG);
    }

    @Bean
    ComputerAuditLocation computerAuditDirectory() {
        return new ComputerAuditLocation(Paths.get(AD_ROOT), COMP_LOG);
    }

    @Bean
    DomainAuditLocation domainAuditDirectory() {
        return new DomainAuditLocation(Paths.get(AD_ROOT), DOMAIN_LOG);
    }

    @Bean
    PrinterConnectionAuditLocation printerConnectionAuditDirectory() {
        return new PrinterConnectionAuditLocation(Paths.get(PRN_ROOT), LOG_FILTER);
    }

    @Bean
    UserAuditContent userAuditContentConverter() {
        return new UserAuditContent();
    }

    @Bean
    ComputerAuditContent computerAuditContentConverter() {
        return new ComputerAuditContent();
    }

    @Bean
    DomainAuditContent domainAuditContentConverter() {
        return new DomainAuditContent();
    }

    @Bean
    PrinterConnectionAuditContent printerConnectionAuditContentConverter() {
        return new PrinterConnectionAuditContent();
    }

    @Bean
    UserAuditLoader userAuditLoader() {
        return new UserAuditLoader(userDao, userAuditContentConverter(), userAuditDirectory());
    }

    @Bean
    ComputerAuditLoader computerAuditLoader() {
        return new ComputerAuditLoader(computerDao, computerAuditContentConverter(), computerAuditDirectory());
    }

    @Bean
    DomainAuditLoader domainAuditLoader() {
        return new DomainAuditLoader(domainDao, domainAuditContentConverter(), domainAuditDirectory());
    }

    @Bean
    PrinterConnectionAuditLoader printerConnectionAuditLoader() {
        return new PrinterConnectionAuditLoader(
                printerConnectionDao, printerConnectionAuditContentConverter(), printerConnectionAuditDirectory());
    }

    @Bean
    AuditImport auditImport() {
        AuditImport batch = new AuditImport();
        batch.addLoader(domainAuditLoader());
        batch.addLoader(userAuditLoader());
        batch.addLoader(computerAuditLoader());
        batch.addLoader(printerConnectionAuditLoader());
        return batch;
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
